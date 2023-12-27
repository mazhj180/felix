package com.mazhj.common.core.excel;

import com.mazhj.common.core.exception.ExcelParseException;
import lombok.Getter;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author mazhj
 */
@Getter
public class PoiExcelDefinition {

    private final List<Sheet> sheets = new ArrayList<>();

    private final Map<Sheet,List<Row>> rowMap = new HashMap<>();

    private final Map<Row,List<Cell>> cellMap = new HashMap<>();

    public PoiExcelDefinition(File excel) throws IOException, ExcelParseException {
        String[] suffix = {".xlsx",".xls"};
        String fileName = excel.getName();
        if (!fileName.endsWith(suffix[0]) && !fileName.endsWith(suffix[1])){
            throw new ExcelParseException("不是excel文件");
        }
        try(Workbook workbook = WorkbookFactory.create(excel)){
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            while (sheetIterator.hasNext()){
                Sheet sheet = sheetIterator.next();
                sheets.add(sheet);
                List<Row> rows = new ArrayList<>();
                Iterator<Row> rowIterator = sheet.rowIterator();
                while (rowIterator.hasNext()){
                    Row row = rowIterator.next();
                    rows.add(row);
                    List<Cell> cells = new ArrayList<>();
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()){
                        Cell cell = cellIterator.next();
                        cells.add(cell);
                    }
                    cellMap.put(row,cells);
                }
                rowMap.put(sheet,rows);
            }
        }
    }

    public List<List<String>> sheetValForStrToList(Sheet sheet){
        List<List<String>> valForStr = new ArrayList<>();
        for (Row row : rowMap.get(sheet)) {
            List<String> cellValForStr = new ArrayList<>();
            for (Cell cell : cellMap.get(row)) {
                Object val = switch (cell.getCellType()) {
                    case STRING -> cell.getStringCellValue();
                    case NUMERIC -> cell.getNumericCellValue();
                    case BOOLEAN -> cell.getBooleanCellValue();
                    default -> null;
                };
                if (val != null){
                    cellValForStr.add(val.toString());
                }
            }
            valForStr.add(cellValForStr);
        }
        return valForStr;
    }

    public List<List<String>> sheetValForStrToList(Integer index){
        if (index >= sheets.size()){
            index = sheets.size() - 1;
        }
        Sheet sheet = sheets.get(index);
        return sheetValForStrToList(sheet);
    }

    public Stream<String> allValForStrToStream(){
        Stream<String> all = Stream.empty();
        for (Sheet sheet : sheets) {
            Stream<String> one = Stream.empty();
            for (List<String> rows : sheetValForStrToList(sheet)) {
                one = Stream.concat(rows.stream(),one);
            }
            all = Stream.concat(one,all);
        }
        return all;
    }

}

