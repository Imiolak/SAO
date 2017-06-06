package agh.sao.reversi.tournament;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Experiment {

    private final String pathToResultsFolder = "../../results/";
    private final int iterations;
    private final ISpecimenCrossover specimenCrossover;
    private final ISpecimenSuccessionStrategy specimenSuccession;
    private final ITournamentPerformer tournamentPerformer;

    private List<StrategySpecimen> population;

    public Experiment(int iterations, ISpecimenCrossover specimenCrossover,
                      ISpecimenSuccessionStrategy specimenSuccession, ITournamentPerformer tournamentPerformer) {
        this.iterations = iterations;
        this.specimenCrossover = specimenCrossover;
        this.specimenSuccession = specimenSuccession;
        this.tournamentPerformer = tournamentPerformer;
    }

    public void injectInitialPopulation(List<StrategySpecimen> initialPopulation) {
        this.population = initialPopulation;
    }

    public void performAndWriteResults() {
        performAndWriteResults(false);
    }

    public void performAndWriteResults(boolean debug) {
        System.out.println("Starting experiment..");
        File outputFolder = createResultsFolder();

        for (int i = 0; i < iterations - 1; i++) {
            System.out.println("iteration " + i);
            List<StrategySpecimen> newPopulation = new ArrayList<>();

            for (int t = 0; t < population.size() / specimenSuccession.getNumberOfSpecimensAdvancingInEachStep(); t++) {
                System.out.println("tournament " + t);

                StrategySpecimen[] tournamentCompetitors = PopulationUtils.getRandomSpecimens(population, 10);
                tournamentPerformer.setCompetingStrategies(tournamentCompetitors);
                tournamentPerformer.performTournament();
                StrategySpecimen[] parentSpecimens = tournamentPerformer.getTopPerformers(2);
                StrategySpecimen childSpecimen = specimenCrossover.doCrossover(parentSpecimens[0], parentSpecimens[1]);

                specimenSuccession.decideSuccessors(newPopulation, parentSpecimens, childSpecimen);
            }

            if (newPopulation.size() < population.size()) {
                StrategySpecimen[] wildCardSpecimens = PopulationUtils.getRandomSpecimens(population, population.size() - newPopulation.size());
                for (StrategySpecimen specimen : wildCardSpecimens) {
                    newPopulation.add(specimen);
                }
            }

            if (debug) {
                writeResults(outputFolder, population, Integer.toString(i));
            }

            population = newPopulation;
        }

        for (int t = 0; t < population.size(); t++) {
            StrategySpecimen[] tournamentCompetitors = PopulationUtils.getRandomSpecimens(population, 10);
            tournamentPerformer.setCompetingStrategies(tournamentCompetitors);
            tournamentPerformer.performTournament();
        }

        writeResults(outputFolder);
    }

    private void writeResults(File resultsFolder) {
        writeResults(resultsFolder, population, "final");
    }

    private void writeResults(File resultsFolder, List<StrategySpecimen> population, String fileName) {
        try {
            File outputFile = new File(resultsFolder.getAbsolutePath() + "/" + fileName);
            outputFile.createNewFile();
            FileWriter writer = new FileWriter(outputFile);

            for (StrategySpecimen specimen : population.stream()
                    .sorted(Comparator.comparingDouble(StrategySpecimen::getWinningPercentage).reversed())
                    .collect(Collectors.toList())) {

                writer.write(specimen.getResultsString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File createResultsFolder() {
        String fullPathToResultsFolder = pathToResultsFolder + new Date().toString();
        File resultsFolder = new File(fullPathToResultsFolder);
        resultsFolder.mkdir();

        return resultsFolder;
    }
}
