import java.util.*;
import java.io.*;
public class SearchEngine{
// 	Write a Java class SearchEngine. This is the class that we will use as
// an interface to the search engine. It should contain following methods:
// – SearchEngine(): This is the constructor method. It should cre-
// ate an empty InvertedPageIndex.
// – void performAction(String actionMessage): This the main
// stub method that you have to implement. It takes an action as
// a string. The list of 
	// actions, and their format will be described
// later.
	// int totalpages ;
	InvertedPageIndex database;
	MyLinkedList<String> pages;
	public SearchEngine(){
		database = new InvertedPageIndex();
		// totalpages = 0;
		pages = new MyLinkedList<String>();
	}
	public void changeWords(String words[])
	{	int i=0;
		for(String word: words)
		{
			if(word.equals("stacks"))
				{
					words[i]="stack";
				}
			if(word.equals("structures"))
				{
					words[i]="structure";
				}
			if(word.equals("applications"))
				{
					words[i]="application";
				}
			i++;
		};
		return;
	}
	public void performAction(String actionMessage)
	{
		if(actionMessage.contains("addPage"))
		{
			String pageName = actionMessage.substring(8);
			if(pages.contains(pageName))
			{
				System.out.print(pageName+ " is already added!");
			}
			else
			{	
			PageEntry page = new PageEntry(pageName);
			// LinkedList<WordEntry> x = page.getPageIndex().getWordEntries();
			// for(WordEntry a : x)
			// {
			// 	a.print();
			// }
			database.addPage(page);
			// database
			pages.add(pageName);
			// page.getPag
		}
		}
		else if(actionMessage.contains("queryFindPagesWhichContainWord"))
		{
			String word = actionMessage.substring(31);
			if(word.equals("stacks"))
				{
					word="stack";
				}
				if(word.equals("structures"))
				{
					word="structure";
				}
				if(word.equals("applications"))
				{
					word="application";
				}
			MyLinkedList<PageEntry> x = database.getPagesWhichContainWordinOrder(word);
			if(x.size() < 1 || x == null)
				System.out.println("No webpage contains word "+word);
			else
			{	
				for(int i=0;i<x.size();i++)
				{	if(i!=0)
					{
						System.out.print(", ");
					}
					System.out.print(x.elementByIndex(i).name());
				}
			}
		}
		else if(actionMessage.contains("queryFindPositionsOfWordInAPage"))
		{	int i=0;
			for(i=32;i<actionMessage.length();i++)
			{
				if(actionMessage.charAt(i)==' ')
				{
					break;
				}
			}
			String word = actionMessage.substring(32,i);
			String page = actionMessage.substring(i+1);
			word = word.toLowerCase();
			if(word.equals("stacks"))
				{
					word="stack";
				}
				if(word.equals("structures"))
				{
					word="structure";
				}
				if(word.equals("applications"))
				{
					word="application";
				}
			if(pages.contains(page))
			{MyLinkedList<Position> p = database.returnWordEntry(word).getAllPositionsForThisWord();
			int j = 0;
			for(i =0;i<p.size();i++)
			{	
				if(p.elementByIndex(i).getPageEntry().name().equals(page))
				{	if(j!=0)
					System.out.print(", ");
					System.out.print(p.elementByIndex(i).getWordIndex());
					j++;
				}
			}
			if(j==0)
			{
				System.out.print("Webpage "+page+" does not contain word "+word);
			}}
			else
				System.out.print("No webpage "+page+" found");
		}
		else if(actionMessage.contains("queryFindPagesWhichContainAllWords"))
		{								
			String actions = actionMessage.substring(35);
			String[] words = actions.split(" ");
			this.changeWords(words);
			MySet<PageEntry> list = database.getPagesWhichContainAllWords(words);
			if(list.size()<1 || list == null)
			{
				System.out.print("Sorry, No webpage contains all the words in the Phrase");
			}
			else
			{MySet<SearchResult> makelist = new MySet<SearchResult>();
			for(PageEntry page : list.data)
			{	float rel = page.getRelevanceOfPage(words,false,pages.size());
				// System.out.println(page.name()  +" - "+ rel);
				makelist.addElement(new SearchResult(page,rel));
			}
			MySort obj= new MySort();
			ArrayList<SearchResult> finallist = obj.sortThisList(makelist);
			for(int i=0;i<finallist.size();i++)
			{
				if(i!=0)
					System.out.print(", ");
				System.out.print(finallist.get(i).getPageEntry().name());
			}
		}
		}
		else if(actionMessage.contains("queryFindPagesWhichContainAnyOfTheseWords"))
		{
			String actions = actionMessage.substring(42);
			String[] words = actions.split(" ");
			this.changeWords(words);
			MySet<PageEntry> list= database.getPagesWhichContainAnyOfTheseWords(words);
			if(list.size()<1 || list == null)
			{
				System.out.print("Sorry, No webpage contains any of the words in the Phrase");
			}
			else{
			MySet<SearchResult> makelist = new MySet<SearchResult>();
			for(PageEntry page: list.data)
			{
				makelist.addElement(new SearchResult(page,page.getRelevanceOfPage(words,false,pages.size())));
			}
			MySort obj= new MySort();
			ArrayList<SearchResult> finallist = obj.sortThisList(makelist);
			for(int i=0;i<finallist.size();i++)
			{
				if(i!=0)
					System.out.print(", ");
				System.out.print(finallist.get(i).getPageEntry().name());
			}}
		}
		else if(actionMessage.contains("queryFindPagesWhichContainPhrase"))
		{
			String actions = actionMessage.substring(33);
			String[] words = actions.split(" ");
			this.changeWords(words);
			MySet<PageEntry> list= database.getPagesWhichContainPhrase(words);
			if(list.size()<1 || list == null)
			{
				System.out.print("Sorry, No webpage contains the Phrase");
			}
			else{
			MySet<SearchResult> makelist = new MySet<SearchResult>();
			for(PageEntry page: list.data)
			{
				float rel = page.getRelevanceOfPage(words,true,pages.size());
				// System.out.println(page.name()  +" - "+ rel);
				makelist.addElement(new SearchResult(page,rel));
			}
			MySort obj= new MySort();
			ArrayList<SearchResult> finallist = obj.sortThisList(makelist);
			for(int i=0;i<finallist.size();i++)
			{
				if(i!=0)
					System.out.print(", ");
				System.out.print(finallist.get(i).getPageEntry().name());
			}}
		}
		else
		{
			System.out.print("Error - No actions found with actionMessage: "+actionMessage);
		}

	}
	public static void main(String[] args) {
		SearchEngine test = new SearchEngine();
		Scanner s = new Scanner(System.in);
		while(s.hasNextLine())
		{	String actionMessage= s.nextLine();
			test.performAction(actionMessage);
			System.out.println("");
		}
	}
}