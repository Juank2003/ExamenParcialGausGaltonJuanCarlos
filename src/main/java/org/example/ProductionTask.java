package org.example;

import java.util.Random;
import java.util.concurrent.BlockingQueue;


public class ProductionTask implements Runnable {
    private final BlockingQueue<Integer> buffer;
    private final Visualizer visualizer;
    private final int workstationId;
    private int[] distribution; // Estructura para rastrear la distribución de componentes
    private Random random = new Random();

    public ProductionTask(int workstationId, BlockingQueue<Integer> buffer, Visualizer visualizer) {
        this.workstationId = workstationId;
        this.buffer = buffer;
        this.visualizer = visualizer;
        this.distribution = new int[100]; // Inicializa con el número apropiado de contenedores
    }

    @Override
    public void run() {
        while (true) {
            try {
                // Simula la producción de componentes
                int component = produceComponent();

                // Actualiza la estructura de distribución
                updateDistribution(component);

                // Coloca el componente en el búfer compartido
                buffer.put(component);

                // Llama al método para reflejar la distribución en el visualizador
                visualizer.updateDistribution(distribution);

                // Agrega una pausa para simular la producción
                Thread.sleep(500); // Ajusta el tiempo según tus necesidades

                // Simula el ensamblaje de la máquina
                assembleMachine(component);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    //Esto es un metodo de Assemblilyne
    private void assembleMachine(int component) {
        // Simula el ensamblaje de la máquina
        System.out.println("Ensamblaje de componente " + component + " en " + Thread.currentThread().getName());
        // Implementa tu lógica de ensamblaje aquí
        // Por ejemplo, puedes simular el tiempo que lleva el ensamblaje con una pausa.
        try {
            Thread.sleep(100); // Simula un tiempo de ensamblaje de 100 milisegundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Ensamblaje de componente " + component + " completado en " + Thread.currentThread().getName());
    }

    private int produceComponent() {
        // Simula la producción de componentes
        return random.nextInt(100);
    }

    private void updateDistribution(int component) {
        // Implementa la lógica para actualizar la distribución con el nuevo componente
        // Puede ser necesario calcular en qué contenedor debe colocarse el componente
        // y aumentar la cantidad en ese contenedor.
        // Asegúrate de manejar adecuadamente los índices y límites del array.
    }
}


