import java.util.*;
public class WordEntry{
	MyLinkedList<Position> globalindex= new MyLinkedList<Position>();
	MyLinkedList<String> allNames=new MyLinkedList<String>();
	AVLtree tree;
	String root;
	public WordEntry(String word)
	{	
		if(word.length()>1)
		{//System.out.println("WordEntry created: "+word);
		root = word;
		tree = new AVLtree(word);}
	}
	public Boolean equals(String str)
	{	
		return (root.equals(str));
	}
	public String getWord()
	{
		return root;
	}
	public void print()
	{
		System.out.print(root);
		for(int i=0;i<globalindex.size();i++)
		{
			globalindex.elementByIndex(i).print();
		}
		System.out.println("");
	}
	public void addPosition(Position position)
	{	Position p = new Position(position);
		globalindex.add(p); tree.insert(p); 
		if(!allNames.contains(position.getPageEntry().name()))
		{
			allNames.add(position.getPageEntry().name());
		}
	}
	public void addPositions(MyLinkedList<Position> positions)
	{	if(positions.size()>0 && positions != null)
		// globalindex.append(positions);
		for(int i=0;i<positions.size();i++)
		{
			this.addPosition(positions.elementByIndex(i));
		}
		// tree.insert(positions);
	}
	public MyLinkedList<Position> getAllPositionsForThisWord()
	{
		return globalindex;
	}
	public AVLtree getTreeForThisWord()
	{
		return tree;
	}
	public MyLinkedList<PageEntry> getPageEntryinOrder()
	{
		MyLinkedList<PageEntry> list = new MyLinkedList<PageEntry>();
		for(int i=0;i<globalindex.size();i++)
		{
			list.add(globalindex.elementByIndex(i).getPageEntry());
		}
		MyLinkedList<PageEntry> sortedlist = new MyLinkedList<PageEntry>();
		while(list.size()>0)
		{	int j = 0;
			float max = 0;
			for(int i =0;i<list.size();i++)
			{
				if(list.elementByIndex(i).getTermFrequency(root)>max)
				{
					j = i;
				}
			}
			sortedlist.add(list.elementByIndex(j));
			list.removeByIndex(j);
		}
		return sortedlist;
	}
	public MySet<PageEntry> getAllPageEntries()
	{
		MySet<PageEntry> desired = new MySet<PageEntry>();
		for(int i =0;i<globalindex.size();i++)
		{
			desired.addElement(globalindex.elementByIndex(i).getPageEntry());
		}
		return desired;
	}
	public Boolean returnArrayListOfTheFirstWordOfAPhraseInAPage(PageEntry page,ArrayList<Integer> x, String str)
	{	//System.out.println("root: "+root+"str: "+ str);
		if(root.equals(str))
		{//System.out.println("going to tree for first");
			tree.returnArrayListOfTheFirstWordOfAPhraseInAPage(page,x);return true;}
		return false;
	} 
	public Boolean editPhraseArrayList(PageEntry page, ArrayList<Integer> x,String str,int index)
	{
		if(root.equals(str))
		{
			tree.editPhraseArrayList(page,x,index);return true;
		}
		return false;
	}
	public int frequency()
	{
		return globalindex.size();
	}
	public int getFrequency(String str)
	{
		if(root.equals(str))
		{
			return this.frequency();
		}
		return 0;
	}
	public int frequency(PageEntry page)
	{
		int number=0;
		for(int i=0;i<globalindex.size();i++)
		{
			if(globalindex.elementByIndex(i).getPageEntry().name().equals(page.name()))
				number++;
		}
		return number;
	}
	public int getFrequency(PageEntry page,String str)
	{
		if(root.equals(str))
		{
			return this.frequency(page);
		}
		return 0;
	}
	public int numberOfPages(String str)
	{	if(root.equals(str))
		return allNames.size();
		return 0;
	}
	public Boolean mergeWord(WordEntry newWord)
	{	Boolean a = this.root.equals(newWord.getWord());
		if(a)
		{	
			this.addPositions(newWord.getAllPositionsForThisWord());
			return true;
		}
		return false;
	}
}