import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        String out = "";

        String lower = s;
        String[] words = lower.split(" ");

        for(int i = 0; i < words.length; i++) {
            String k = words[i];
            int v = map.getOrDefault(k, 0) + 1;
            map.put(k, v);
        }

        Iterator it = map.entrySet().iterator();
        WordFrequency[] wf = new WordFrequency[map.size()];
        int count = 0;

        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            String w = (String) e.getKey();
            int f = (int) e.getValue();
            WordFrequency t = new WordFrequency(w, f);
            wf[count] = t;
            count++;
        }

        Arrays.sort(wf);

        for(WordFrequency i : wf) {
            for(int j = 0; j < i.freq; j++) {
                out = out + i.word + " ";
            }
        }
        System.out.println(out);
        return out;
    }
}


