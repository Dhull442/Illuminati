import java.util.*	;
public class MySet<T>{
	public Set<T> data= new HashSet<T>();
	public void addElement(T element)
	{
		data.add(element);
	}
	public int size()
	{
		return data.size();
	}
	public Boolean remove(T element)
	{
		return data.remove(element); 
	}
	public MySet<T> union(MySet<T> otherSet)
	{
		MySet<T> un= new MySet<T>();
		un.data = data;
		un.data.addAll(otherSet.data);
		return un;
	}
	public MySet<T> intersection(MySet<T> otherSet)
	{
		MySet<T> inter= new MySet<T>();
		inter.data = data;
		inter.data.retainAll(otherSet.data);
		return inter;
	}
}