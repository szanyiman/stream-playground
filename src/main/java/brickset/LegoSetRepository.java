package brickset;
import repository.Repository;
import java.util.Comparator;
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Prints the first 'number' LEGO sets name
     */
    public void printLegoSetsNameWithLimit(int number) {
        getAll().stream().
                map(legoSet -> legoSet.getName()).
                limit(number).
                forEach(System.out::println);
    }

    /**
     * Returns the number of LEGO sets which are over the specified number
     *
     * @param number a LEGO set number
     * @return the number of LEGO sets which are over the specified number
     */
    public long countLegoSetsWithPieces(int number) {
        return getAll().stream()
                .filter(legoSet -> legoSet.getPieces()>number)
                .count();
    }

    /**
     * Returns the average number of the LEGO sets pieces
     *
     * @return the average number of the LEGO sets pieces
     */
    public double printLegoSetsAverageOfPieces() {
        return getAll().stream().
                mapToLong(legoSet -> legoSet.getPieces()).
                average().
                getAsDouble();
    }

    /**
     * Prints every LEGO sets name in reverse alphabetical order
     */
    public void printLegoSetsNameInReverseAlphabeticalOrder() {
        getAll().stream().
                map(legoSet -> legoSet.getName()).
                sorted(Comparator.nullsLast(Comparator.reverseOrder())).
                forEach(System.out::println);
    }

    /**
     * Returns the list of LEGO sets with the subtheme specified
     *
     * @param subtheme a LEGO set subtheme
     * @return the list of LEGO sets with the subtheme specified
     */

    public void printLegoSetsName(String subtheme) {
        getAll().stream().
                filter(legoSet -> legoSet.getSubtheme() != null && legoSet.getSubtheme().contains(subtheme)).
                map(legoSet -> legoSet.getName()).
                forEach(System.out::println);
    }

    public static void main(String[] args) {

        var repo = new LegoSetRepository();
        repo.printLegoSetsNameWithLimit(10);
        System.out.println(repo.countLegoSetsWithPieces(230));
        System.out.println(repo.printLegoSetsAverageOfPieces());
        repo.printLegoSetsNameInReverseAlphabeticalOrder();
        repo.printLegoSetsName("Ferrari");

    }
}
