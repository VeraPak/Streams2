import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 1_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long teens = persons.stream()
                .filter(p -> p.getAge() >= 18)
                .count();

        List<String> warrers = persons.stream()
                .filter(p -> p.getAge() >= 18 && p.getAge() <= 27)
                .filter(p -> p.getSex() == Sex.MAN)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        List<String> smarts = persons.stream()
                .filter(e -> e.getEducation() == Education.HIGHER)
                .filter(s -> s.getAge() > 18 && s.getAge() < (s.getSex() == Sex.MAN ? 65 : 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .map(Person::getFamily)
                .collect(Collectors.toList());
    }
}
