package pl.wsb.fitnesstracker.training.internal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.api.TrainingProvider;
import pl.wsb.fitnesstracker.training.api.TrainingRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.internal.UserRepository;

// TODO: Provide Implementation and correct the return type of the method getTraining
@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingServiceImpl implements TrainingProvider {

    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<Training> getTraining(final Long trainingId) {
        return trainingRepository.findById(trainingId);
    }

    public List<Training> findAllTrainings() {
        return trainingRepository.findAll();
    }

    public List<Training> findTrainingsByUserId(Long userId) {
        return trainingRepository.findAll()
                .stream()
                .filter(training -> training.getUser().getId().equals(userId))
                .toList();
    }
    public void generateWeeklyReports() {
        Date sevenDaysAgo = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
        List<User> allUsers = userRepository.findAll();
        log.info("Rozpoczynanie generowania raportów tygodniowych...");

        for (User user : allUsers) {
            List<Training> userTrainings = trainingRepository.findAllByUserIdAndStartTimeAfter(user.getId(), sevenDaysAgo);

            System.out.println("=== RAPORT DLA: " + user.getEmail() + " ===");
            if (userTrainings.isEmpty()) {
                System.out.println("Brak aktywności w ostatnim tygodniu.");
            } else {
                userTrainings.forEach(t -> System.out.println(
                        "Trening: " + t.getActivityType() +
                                " | Data: " + t.getStartTime() +
                                " | Dystans: " + t.getDistance() + " km"
                ));
            }
            System.out.println("==========================================");
        }
    }

}
