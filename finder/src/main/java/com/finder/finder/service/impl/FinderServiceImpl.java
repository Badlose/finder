package com.finder.finder.service.impl;

import com.finder.finder.exception.*;
import com.finder.finder.service.FinderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class FinderServiceImpl implements FinderService {

    @Override
    public Long findNumber(String filePath, int n) {
        log.info("Find number was  called with params: [%s], [%s]".formatted(filePath, n));
        Path path = Paths.get(filePath);
        checkN(n);
        checkFilePath(path);
        List<Long> numbers = getNumbersFromXlsx(filePath);
        if (numbers.isEmpty()) {
            throw new FileHasNoNumbersException(path.toString());
        }
        if (n > numbers.size()) {
            throw new NIsGreaterThanTableMaxValueException(String.valueOf(n));
        }
        long result = scanNumbers(numbers, 0, numbers.size() - 1, n - 1);
        log.info("Find number method result: [%s]".formatted(result));
        return result;
    }

    private void checkFilePath(Path path) {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            throw new FileDoesNotExistException(path.toString());
        }
        if (!path.toString().endsWith(".xlsx")) {
            throw new FilePathIsNotXLSXException(path.toString());
        }
    }

    private void checkN(int n) {
        if (n <= 0) {
            throw new InvalidNException(String.valueOf(n));
        }
    }

    private List<Long> getNumbersFromXlsx(String filePath) {
        Set<Long> uniqueNumbers = new HashSet<>();
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null) {
                    if (cell.getCellType() == CellType.NUMERIC) {
                        long value = (long) cell.getNumericCellValue();
                        uniqueNumbers.add(value);
                    } else {
                        throw new IncorrectCellFormatException(cell.getCellType().toString());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileDoesNotExistException(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return new ArrayList<>(uniqueNumbers);
    }

    private long scanNumbers(List<Long> numbers, int left, int right, int k) {
        if (left == right) {
            return numbers.get(left);
        }
        int randomIndex = (int) (left + Math.random() * (right - left + 1));
        int pivotIndex = partitioning(numbers, left, right, randomIndex);

        if (pivotIndex == k) {
            return numbers.get(pivotIndex);
        } else if (k < pivotIndex) {
            return scanNumbers(numbers, left, pivotIndex - 1, k);
        } else {
            return scanNumbers(numbers, pivotIndex + 1, right, k);
        }
    }

    private int partitioning(List<Long> numbers, int left, int right, int pivotIndex) {
        long currentValue = numbers.get(pivotIndex);
        swap(numbers, pivotIndex, right);
        int tempIndex = left;
        for (int i = left; i < right; i++) {
            if (numbers.get(i) < currentValue) {
                swap(numbers, tempIndex, i);
                tempIndex++;
            }
        }
        swap(numbers, tempIndex, right);
        return tempIndex;
    }

    private void swap(List<Long> numbers, int i, int j) {
        long temp = numbers.get(i);
        numbers.set(i, numbers.get(j));
        numbers.set(j, temp);
    }

}
