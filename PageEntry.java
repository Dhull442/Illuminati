import java.util.*;
import java.io.*;
public class PageEntry{
	String pagename;
	PageIndex pageIndex;
	int totalwords=0;
	private String[] connectwords = {" ","a", "an","it","am","the","that", "they", "these", "this", "for","I","you","You", "is", "are", "was", "of","or", "and", "does", "will", "whose"};
	public PageEntry(String pageName)
	{
		pagename= pageName;
		pageIndex = new PageIndex();
		try{
		FileInputStream fstream = new FileInputStream("webpages/"+pageName);
		Scanner s = new Scanner(fstream);
		int prevsize=0,treeprevsize=0;
		while(s.hasNextLine())
		{
			String x = s.nextLine();
			String[] arr = x.split("\\s+|\\{|}|\\[|]|=|<|>|\\(|\\)|\\.|,|;|'|\"|\\?|#|!|-|:");		
			for(int i=0;i<arr.length;i++)
			{	if(arr[i].length()>1)
				{arr[i]=arr[i].toLowerCase();
				Boolean is = false;
				for(int j=0;j<connectwords.length;j++)
				{
					if(connectwords[j].equals(arr[i]))
					{
						is = true; break;
					}
				}
				if(arr[i].equals("stacks"))
				{
					arr[i]="stack";
				}
				if(arr[i].equals("structures"))
				{
					arr[i]="structure";
				}
				if(arr[i].equals("applications"))
				{
					arr[i]="application";
				}
				if(!is)
				{	
					Position p= new Position(this, i+prevsize, treeprevsize);
					pageIndex.addPositionForWord(arr[i],p);
					treeprevsize++;
				}
			prevsize++;}
			}
		}
		totalwords=prevsize+1;
		}
		catch(FileNotFoundException filenotfound)
		{
			System.out.print("File not found!");
		}
	}
	public Boolean containsWord(String str)
	{
		MyLinkedList<WordEntry>entries = new MyLinkedList<WordEntry>();
		entries.createfromlist(pageIndex.getWordEntries());
		for(int i =0; i< entries.size();i++)
		{
			if(entries.elementByIndex(i).getWord().equals(str))
				return true;
		}
		return false;
	}
	public Boolean containsPhrase(String str[])
	{
		int i=0;
		MyLinkedList<WordEntry> entries = new MyLinkedList<WordEntry>();
		entries.createfromlist(pageIndex.getWordEntries());
		// for(int k=0;k<entries.size();k++)
		// 	{
		// 		entries.elementByIndex(k).print();
		// 	}
		ArrayList<Integer> allOccurances = new ArrayList<Integer>();
		for(String s : str)
		{	//System.out.println(entries.size());
			for(int j=0;j<entries.size();j++)
			{
				if(i==0)
				{	//System.out.println("first !done");
					if(entries.elementByIndex(j).returnArrayListOfTheFirstWordOfAPhraseInAPage(this,allOccurances,s))
						{/*System.out.println("first done")*/;break;}
				}
				else
				{
					if(entries.elementByIndex(j).editPhraseArrayList(this, allOccurances,s,i));
						break;
				}
				if(j==entries.size()-1)
					{/*System.out.println("j=entries-1");*/return false; }
			}
			if(allOccurances.size()<1)
			{
				return false;
			}
			i++;
		}
		// System.out.print("m is greater than 0");
		int m = allOccurances.size();
		//System.out.println(m);
		if(m>0)
			return true;
		return false;
	}
	public PageIndex getPageIndex()
	{
		return pageIndex;
	}
	public String name()
	{
		return pagename;
	}
	public float getTermFrequency(String str[])
	{
		int k = str.length - 1;int i=0;
		MyLinkedList<WordEntry> entries = new MyLinkedList<WordEntry>();entries.createfromlist(pageIndex.getWordEntries());
		ArrayList<Integer> allOccurances = new ArrayList<Integer>();
		for(String s : str)
		{
			for(int j=0;j<entries.size();j++)
			{
				if(i==0)
				{
					if(entries.elementByIndex(j).returnArrayListOfTheFirstWordOfAPhraseInAPage(this,allOccurances,s))
						break;
				}
				else
				{
					if(entries.elementByIndex(j).editPhraseArrayList(this, allOccurances,s,i));
						break;
				}
				if(j==entries.size()-1)
					return 0;
			}
			if(allOccurances.size()<1)
			{
				return 0;
			}
			i++;
		}
		int m = allOccurances.size();
		return (float)m/(float)(totalwords - k* m);
	}
	public float getTermFrequency(String word)
	{
		int freq=0;
		LinkedList<WordEntry> list = pageIndex.getWordEntries();
		for(int i =0;i<list.size();i++)
		{
			freq+=list.get(i).getFrequency(this,word);
		}
		return (float)freq/(float)totalwords;
	}
	public float idf(String str)
	{
		// return 
		int total=0;
		LinkedList<WordEntry> list = pageIndex.getWordEntries();
		for(int i =0;i<list.size();i++)
		{
			total+=list.get(i).numberOfPages(str);
		}
		if(total!=0)
		return -(float)Math.log10(total);
		return 0;
	}
	/*float getRelevanceOfPage(String str[ ], boolean doThese-
WordsRepresentAPhrase): Return the relevance of the webpage
for a group of words represented by the array str[ ]. If the flag
doTheseWordsRepresentAPhrase is true, it means that the words
represent a phrase; otherwise the words are part of a complex
query (AND/OR).*/
	public float getRelevanceOfPage(String str[ ], Boolean doTheseWordsRepresentAPhrase,int allPages)
	{	//System.out.print("Calculating relevance");
		if(doTheseWordsRepresentAPhrase)
		{
			return getTermFrequency(str);
		}
		else
		{	float rel=0;
			for(String s : str)
			{
				rel += this.getTermFrequency(s) * ((float)Math.log10(allPages) + this.idf(s));
			}
			return rel;
		}
	}
	public void print()
	{
		pageIndex.print();
	}
}