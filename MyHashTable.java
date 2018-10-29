import java.util.*;
import java.lang.reflect.Array; 
public class MyHashTable{
	MyLinkedList< MyLinkedList<WordEntry> > alist = new MyLinkedList< MyLinkedList<WordEntry> >() ;
	public MyHashTable()
	{	
		for(int i =0;i<26;i++)
		{
			alist.add(new MyLinkedList<WordEntry>());
		}
	}
	private int getHashIndex(String str)
	{	
		str=str.toLowerCase();
		return Math.abs(((int)str.charAt(0)-97)%26);
	}
	public void addPositionsForWord(WordEntry w)
	{	
		if(w.getWord().length() > 0)
		{int index = this.getHashIndex(w.getWord());
		MyLinkedList<WordEntry> ilist = alist.elementByIndex(index);
		for(int i=0;i<ilist.size();i++)
		{
			if(ilist.elementByIndex(i).mergeWord(w))
			{	
				return;
			}
		}
		ilist.add(w);
		alist.replace(index,ilist);
		}
	}
	public void addPositionsForWords(LinkedList<WordEntry> list)
	{
		for(int i=0;i<list.size();i++)
		{
			this.addPositionsForWord(list.get(i));
		}
	}
	public void addPositionsForWordsInTree(LinkedList<WordEntry> list)
	{

	}
	public WordEntry returnWord(String str)
	{	str= str.toLowerCase();
		int index = this.getHashIndex(str);
		MyLinkedList<WordEntry> ilist = alist.elementByIndex(index);
		for(int i=0;i<ilist.size();i++)
		{	
			if(ilist.elementByIndex(i).equals(str))
			{
				return ilist.elementByIndex(i);
			}
		}
		return new WordEntry("not_found");
	}
	public void print()
	{
		for(int i =0;i<26;i++)
		{
			System.out.println(alist.elementByIndex(i).size());
		}
	}
}