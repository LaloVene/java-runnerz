package dev.lalovene.runnerz.run;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class RunRepository {

    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    Optional<Run> findById(Integer id) {
        return runs.stream().filter(run -> run.id() == id).findFirst();
    }

    // POST
    void create(Run run) {
        runs.add(run);
    }

    // UPDATE
    void update(Run run, Integer id) {
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()) {
            runs.set(runs.indexOf(existingRun.get()), run);
        } else {
            throw new IllegalArgumentException("Run not found");
        }
    }

    // DELETE
    void delete(Integer id) {
        Optional<Run> run = findById(id);
        if (run.isPresent()) {
            runs.remove(run.get());
        } else {
            throw new IllegalArgumentException("Run not found");
        }
    }

    @PostConstruct
    private void init() {

        runs.add(new Run(1, "Monday run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 3,
                Location.OUTDOOR));
        runs.add(new Run(2, "Wednesday Run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 3,
                Location.OUTDOOR));

    }
}
