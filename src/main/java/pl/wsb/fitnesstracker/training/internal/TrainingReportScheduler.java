package pl.wsb.fitnesstracker.training.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TrainingReportScheduler {

    private final TrainingServiceImpl trainingService;
    @Scheduled(fixedRate = 10000)
    public void scheduleWeeklyReport() {
        trainingService.generateWeeklyReports();
    }
}