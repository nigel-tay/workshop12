package workshop12.workshop.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class RandomNumService {
    
    public List<Integer> generateRandomNumber(int number) {
        return new Random()
                    .ints(1, 31)
                    .distinct()
                    .limit(number)
                    .boxed()
                    .collect(Collectors.toList());
    }
}
