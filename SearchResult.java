import java.util.*;
public class SearchResult implements Comparable<SearchResult>{
	PageEntry page;
	float relevance;
	public SearchResult(PageEntry p,float r)
	{
		page = p;  relevance=r;
	}
	public PageEntry getPageEntry(){
		return page;
	}
	public float getRelevance()
	{
		return relevance;
	}
	public int compareTo(SearchResult otherObject)
	{
		if(this.relevance>otherObject.getRelevance())
			return 1;
		else if(this.relevance<otherObject.getRelevance())
			return -1;
		return 0;
	}
	public void print()
	{
		System.out.println("("+page.name()+","+relevance+")");
	}

}
// Write a Java class SearchResult which represents a tuple<page p, rel-
// evance r>. SearchResult implements the Java interface Comparable
// (http://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html).
// – public SearchResult(PageEntry p, float r): Constructor method.
// – public PageEntry getPageEntry(): Return p.
// – public float getRelevance(): Return r.
// – public int compareTo(SearchResult otherObject): Gives the
// ordering between the current object and the otherObject.