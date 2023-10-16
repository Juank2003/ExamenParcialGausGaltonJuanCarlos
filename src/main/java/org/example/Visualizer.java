package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class Visualizer extends JFrame {
    private int[] distribution;
    private DistributionPanel distributionPanel;
    private ScheduledExecutorService executorService;

    public Visualizer() {
        distribution = new int[100]; // Ejemplo: 100 contenedores
        distributionPanel = new DistributionPanel();
        executorService = Executors.newScheduledThreadPool(1);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        add(distributionPanel);

        // Programa la tarea de actualización cada cierto intervalo
        executorService.scheduleAtFixedRate(this::updateDistributionTask, 0, 500, TimeUnit.MILLISECONDS);

        setVisible(true);
    }

    private void updateDistributionTask() {
        // Lógica para actualizar la distribución en tiempo real
        distribution = generateRandomDistribution();
        distributionPanel.repaint();
    }

    // Esta es solo una distribución aleatoria de ejemplo, debes adaptarla a tus necesidades
    private int[] generateRandomDistribution() {
        int[] randomDistribution = new int[100];
        Random random = new Random();
        for (int i = 0; i < randomDistribution.length; i++) {
            randomDistribution[i] = random.nextInt(400); // Ejemplo: valores aleatorios
        }
        return randomDistribution;
    }

    public void updateDistribution(int[] newDistribution) {
        // Este método ya no se utiliza para actualizar en tiempo real
    }

    private class DistributionPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getWidth() / distribution.length;

            for (int i = 0; i < distribution.length; i++) {
                int x = i * width;
                int barHeight = distribution[i];

                // Dibuja las barras con colores basados en la cantidad de componentes
                g.setColor(Color.BLUE); // Puedes personalizar el color
                g.fillRect(x, getHeight() - barHeight, width, barHeight);
            }
        }
    }
}