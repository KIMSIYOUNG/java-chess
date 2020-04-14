package publisher;


public interface Publisher {
	void add(Observer observer);

	void delete(Observer observer);

	void notify(Observer observer);

}
