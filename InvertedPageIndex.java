import java.util.*;
public class InvertedPageIndex{
	MyHashTable ipi;
	public InvertedPageIndex()
	{
		ipi = new MyHashTable();
	}
	public void addPage(PageEntry p)
	{
		ipi.addPositionsForWords( p.getPageIndex().getWordEntries() );
	}
	public WordEntry returnWordEntry(String str)
	{
		return ipi.returnWord(str);
	}
	public MySet<PageEntry> getPagesWhichContainWord(String str)
	{
		WordEntry w = ipi.returnWord(str);
		// w.print();
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return w.getAllPageEntries();
	}
	public MyLinkedList<PageEntry> getPagesWhichContainWordinOrder(String str)
	{
		WordEntry w = ipi.returnWord(str);
		if(!w.getWord().equals("not_found"))
		return w.getPageEntryinOrder();
		return new MyLinkedList<PageEntry>();
	}
	MySet<PageEntry> getPagesWhichContainAllWords(String str[])
	{	int i =0;
		if(str.length > 1)
		{
			MySet<PageEntry> list = new MySet<PageEntry>();
			for(String s: str)
			{	MySet<PageEntry> n = this.getPagesWhichContainWord(s);
				if(i==0)
				{
					list = n;i++;
				}
				else
				list.intersection(n);
			}
			// list is returned nicely bruh.
			return list;
		}
		return new MySet<PageEntry>();
	}
	MySet<PageEntry> getPagesWhichContainAnyOfTheseWords(String str[])
	{
		if(str.length > 1)
		{
			MySet<PageEntry> list = new MySet<PageEntry>();
			for(String s: str)
			{
				list.union(this.getPagesWhichContainWord(s));
			}
			return list;
		}
		return new MySet<PageEntry>();
	}
	MySet<PageEntry> getPagesWhichContainPhrase(String str[])
	{
		if(str.length>1)
		{
			MySet<PageEntry> list = this.getPagesWhichContainAllWords(str);
			MySet<PageEntry> finallist = new MySet<PageEntry>();
			for(PageEntry page : list.data)
			{	//System.out.print("checking "+page.name());
				if(page.containsPhrase(str))
				{	//System.out.println(page.name()+" has this");
					finallist.addElement(page);
				}
			}
			return finallist;
		}
		return new MySet<PageEntry>();
	}
}
