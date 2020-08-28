public class PersonFactory {
	public static int totalCount = 0;
	public Person[] createPeople(int count, Observer xd) {
		Person people[] = new Person[count];
		for (int x = 0; x < count; x++ ) {
			Person p = new Person(totalCount);
			p.registerObserver(xd);
			people[x] = p;
			totalCount+=1;
			
		}
		return people;
	}
}
