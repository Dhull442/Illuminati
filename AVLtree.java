import java.util.*;
class Node{
	Node left,right;
	int wordIndex;
	MyLinkedList<Position> list = new MyLinkedList<Position>();
	int height;
	public Node()
	{
		left = null;
		right = null;
		wordIndex=-1;
		height=0;
	}
	public Node(Position p)
	{
		left = null;
		right = null;
		wordIndex=p.getTreeIndex();
		list.add(p);
		height=0;
	}
	public int index()
	{
		return wordIndex;
	}
	public void addToList(Position p)
	{
		list.add(p);
	}
	public Boolean containsPosition(Position p)
	{
		if(p==null || p.getTreeIndex()!= wordIndex)
			return false;
		else
		{
			for(int i=0;i<list.size();i++)
			{
				Position check = list.elementByIndex(i);
				if(check.getPageEntry().name().equals(p.getPageEntry().name()))
					return true;
			}
			return false;
		}
	}
	public void printlist()
	{	if(list.size()>0)
		{System.out.print("( ");
		for(int i=0;i<list.size();i++)
		{
			if(i!=0)
			{
				System.out.print(", ");
			}
			list.elementByIndex(i).print();
		}
		System.out.print(" )");}
	}
	public Boolean pageHasWordAtThisIndex(PageEntry page)
	{
		for(int i=0;i<list.size();i++)
		{
			if(list.elementByIndex(i).getPageEntry().name().equals(page.name()))
				return true;
		}
	return false;
	}
	
}
public class AVLtree{
	public String wordName;
	private Node root;
	public AVLtree(String w)
	{
		wordName=w;root = null;
	}
	public AVLtree(){
		root = null;
	}
	public AVLtree(WordEntry w)
	{
		wordName = w.getWord();
		root=null;
		this.insert(w.getAllPositionsForThisWord());
	}
	public Boolean isEmpty()
	{
		return root==null;
	}
	public void makeEmpty()
	{
		root=null;
	}
	public int height(Node x)
	{
		if(x==null)
			return -1;
		else
			return x.height;
	}
	private int max(int lhs,int rhs)
	{
		if(lhs>rhs)
			return lhs;
		return rhs;
	}
	public void insert(MyLinkedList<Position> list)
	{
		for(int i=0;i<list.size();i++)
		{	
			insert(list.elementByIndex(i));
		}
	}
	public void insert(Position p)
	{	if(p!=null)
		root = insert(new Position(p),root);
	}
	private Node insert(Position p,Node here)
	{
		if(here == null)
			here = new Node(p);
		else if(p.getTreeIndex()<here.wordIndex)
		{	//System.out.println(wordName + ", treeindex:"+p.getTreeIndex()+", wordIndex:"+here.wordIndex );
			here.left = insert(p,here.left);
			if(height(here.left)-height(here.right)==2)
			{	//System.out.println("height.left = "+height(here.left)+", height.right = "+ height(here.right));
	        	// this.printInOrder(root.right);
				if(p.getTreeIndex() < here.left.wordIndex)
					{	here=rotatestLeft(here);}
				else if(p.getTreeIndex() > here.left.wordIndex)
					{	
						here=rotateLeftThenRight(here);}
			}
			// System.out.println(wordName + ", treeindex:"+p.getTreeIndex()+", wordIndex:"+here.wordIndex );
		}
		else if(p.getTreeIndex() > here.wordIndex)
		{	//System.out.println(wordName + ", treeindex:"+p.getTreeIndex()+", wordIndex:"+here.wordIndex );
			here.right = insert(p,here.right);
			if(height(here.left)-height(here.right)==-2)
			{
				if(p.getTreeIndex() > here.right.wordIndex)
					{here=rotatestRight(here);}
				else
					{here=rotateRightThenLeft(here);}
			}
		}
		else
		{
			here.addToList(p);
		}
		here.height = max( height(here.left), height(here.right))+1;
		return here;

	}
	private Node rotatestLeft(Node k)
	{
		Node mid = k.left;k.left = mid.right;
		mid.right = k;
		k.height= max(height(k.left),height(k.right))+1;
		mid.height=max(height(mid.left),height(mid.right))+1;
		return mid;
	}
	private Node rotatestRight(Node k)
	{	
		Node mid = k.right;k.right = mid.left;
		mid.left = k;
		k.height= max(height(k.left),height(k.right))+1;
		mid.height=max(height(mid.left),height(mid.right))+1;
		return mid;
	}
	private Node rotateLeftThenRight(Node k)
	{
		Node mid = k.left.right;
		k.left.right = mid.left;mid.left = k.left;
		k.left = mid.right;mid.right = k;
		mid.left.height = max(height(mid.left.left),height(mid.left.right))+1;
		mid.right.height = max(height(mid.right.left),height(mid.right.right))+1;
		mid.height = max(height(mid.left),height(mid.right))+1;
		return mid;
	}
	private Node rotateRightThenLeft(Node k)
	{
		Node mid = k.right.left;
		k.right.left = mid.right;mid.right = k.right;
		k.right = mid.left;mid.left = k;
		mid.left.height = max(height(mid.left.left),height(mid.left.right))+1;
		mid.right.height = max(height(mid.right.left),height(mid.right.right))+1;
		mid.height = max(height(mid.left),height(mid.right))+1;
		return mid;
	}
	private int numberOfNodes(Node belowIncl)
	{
		if(belowIncl == null)
			return 0;
		else
		{
			return 1 + numberOfNodes(belowIncl.left) + numberOfNodes(belowIncl.right);
		}
	}
	public Boolean containsElement(Position p)
	{
		return containsElement(root, p);
	}
	public Boolean containsElement(Node here,Position p)
	{
		if(here == null)
			return false;
		{
			if(here.wordIndex == p.getTreeIndex())
			{

			}
			else if(here.wordIndex > p.getTreeIndex())
			{
				return this.containsElement(here.left,p);
			}
			else
				return this.containsElement(here.right,p);
		}
		return false;
	}
	public void printInOrder()
	{
		printInOrder(root,true);
	}
	public void printInOrder(Node fromhere,Boolean b)
	{
		if(fromhere != null)
		{
			printInOrder(fromhere.left,false);
			if(b)
			{
				System.out.println("<-");
			}
			fromhere.printlist(); System.out.print(" ");
			if(b)
				System.out.println("->");
			printInOrder(fromhere.right,false);
		}
	}
	// public void PrintPretty(Node here,String indent)
 //   	{
 //   		System.out.print(indent);
 //       if(here==null)
 //       {
 //           System.out.print("\\-");
 //           indent += "  ";
 //           return;
 //       }
 //       else
 //       {
 //           System.out.print("|-");
 //           indent += "| ";
 //       }
 //       here.printlist();System.out.println("");
 //       this.PrintPretty(here.left,indent);
 //       this.PrintPretty(here.right,indent);
 //   	}
	public void returnArrayListOfTheFirstWordOfAPhraseInAPage(PageEntry page,ArrayList<Integer> x,Node here)
	{
		if(here != null)
		{
			if(here.pageHasWordAtThisIndex(page))
				x.add(here.wordIndex);
			returnArrayListOfTheFirstWordOfAPhraseInAPage(page,x,here.left);
			returnArrayListOfTheFirstWordOfAPhraseInAPage(page,x,here.right);
		}
		return;
	}
	public void returnArrayListOfTheFirstWordOfAPhraseInAPage(PageEntry page,ArrayList<Integer> x)
	{
		// ArrayList<int> arr = new ArrayList<int>();
		returnArrayListOfTheFirstWordOfAPhraseInAPage(page,x,root);
	}
	public Boolean containsThisIndexInPage(PageEntry page,int index)
	{
		Node temp = root;
		while(temp != null)
		{
			if(index > temp.wordIndex)
				temp = temp.right;
			else if(index < temp.wordIndex)
				temp = temp.left;
			else
			{
				return temp.pageHasWordAtThisIndex(page);
			}
		}
		return false;
	}
	public void editPhraseArrayList(PageEntry page,ArrayList<Integer> x,int index)
	{
		for(int i=0;i<x.size();i++)
		{
			if(!containsThisIndexInPage(page,x.get(i)+index))
			{
				x.remove(x.get(i));
				i--;
			}
		}
	}
}
