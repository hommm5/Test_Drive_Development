
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> repository = new LinkedHashMap<>();

        String line = scanner.nextLine();

        String[] arr = new String[line.length()];

        for (int i = 0; i < line.length(); i++) {
            char caseSensitive = Character.toLowerCase(line.charAt(i));
            arr[i] = caseSensitive+"";
        }


        for (String s : arr) {

            if (repository.containsKey(s)) {
                repository.put(s, repository.get(s) + 1);
            } else {
                repository.put(s, 1);
            }

        }

        repository = repository.entrySet().stream().filter(s -> s.getValue() > 1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(repository.size());
    }

}
/*
Example
"abcde" -> 0 # no characters repeats more than once
"aabbcde" -> 2 # 'a' and 'b'
"aabBcde" -> 2 # 'a' occurs twice and 'b' twice (`b` and `B`)
"indivisibility" -> 1 # 'i' occurs six times
"Indivisibilities" -> 2 # 'i' occurs seven times and 's' occurs twice
"aA11" -> 2 # 'a' and '1'
"ABBA" -> 2 # 'A' and 'B' each occur twice*/
