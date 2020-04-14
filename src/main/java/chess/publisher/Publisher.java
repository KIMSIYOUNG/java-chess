package chess.publisher;


public interface Publisher {
	void add(Observer observer);

	void delete(Observer observer);

	void notifyToAll();

	void notifySpecificObserver(Observer observer);

}
