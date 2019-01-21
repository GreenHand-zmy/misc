package leetcode.heap;

import java.util.*;

public class No347TopKFrequentElements {
    private class Frequent implements Comparable<Frequent> {
        int val;
        int freq;

        public Frequent(int val, int freq) {
            this.val = val;
            this.freq = freq;
        }


        @Override
        public int compareTo(Frequent o) {
            return Integer.compare(this.freq, o.freq);
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        // 建立词频字典
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        PriorityQueue<Frequent> queue = new PriorityQueue<>(k);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (queue.size() < k) {
                queue.add(new Frequent(entry.getKey(), entry.getValue()));
            } else if (entry.getValue() > queue.peek().freq) {
                queue.remove();
                queue.add(new Frequent(entry.getKey(), entry.getValue()));
            }
        }

        List<Integer> res = new ArrayList<>(k);
        while (!queue.isEmpty()) {
            res.add(queue.remove().val);
        }
        return res;
    }
}
