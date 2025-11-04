package ru.mentee.power.solid.isp.task.solution;

import ru.mentee.power.solid.isp.task.solution.client.RefactoredPrintClient;
import ru.mentee.power.solid.isp.task.solution.client.RefactoredScanClient;
import ru.mentee.power.solid.isp.task.solution.device.RefactoredOfficeMFU;
import ru.mentee.power.solid.isp.task.solution.device.RefactoredSimplePrinter;
import ru.mentee.power.solid.isp.task.solution.device.MFUWithStapler;

public class RefactoredDemo {
    public static void main(String[] args) {
        RefactoredSimplePrinter simplePrinter = new RefactoredSimplePrinter();
        RefactoredOfficeMFU officeMFU = new RefactoredOfficeMFU();
        MFUWithStapler fullMFU = new MFUWithStapler();

        RefactoredPrintClient printClient = new RefactoredPrintClient();
        RefactoredScanClient scanClient = new RefactoredScanClient();

        System.out.println("--- Тестирование PrintClient ---");
        printClient.sendToPrint(simplePrinter, "Простой документ на печать.");
        printClient.sendToPrint(officeMFU, "Документ для МФУ на печать.");
        printClient.sendToPrint(fullMFU, "Документ для полного МФУ на печать.");

        System.out.println("\n--- Тестирование ScanClient ---");
        // Следующая строка закомментирована, так как simplePrinter НЕ реализует Scannable — это демонстрирует соблюдение ISP:
        // scanClient.scanDocument(simplePrinter, "Этот вызов вызовет ошибку компиляции!");
        scanClient.scanDocument(officeMFU, "Документ для сканирования на МФУ.");
        scanClient.scanDocument(fullMFU, "Документ для сканирования на полном МФУ.");

        System.out.println("\n--- Тестирование Staple на полном МФУ ---");
        fullMFU.staple("Документ для степления.");
    }
}
