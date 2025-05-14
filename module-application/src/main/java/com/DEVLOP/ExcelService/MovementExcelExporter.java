package com.DEVLOP.ExcelService;

import com.DEVLOP.Entities.Movement;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class MovementExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Movement> movementList;

    public MovementExcelExporter(List<Movement> movementList){
        this.movementList = movementList;
        workbook = new XSSFWorkbook();
    }

    private void WriteHeaderLine(){
        sheet = workbook.createSheet("Movements");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        CreateCell(row, 0, "Movement ID", style);
        CreateCell(row, 1, "Movement Date", style);
        CreateCell(row, 2, "Movement Status", style);
        CreateCell(row, 3, "Access User ID", style);
        CreateCell(row, 4, "Business Unit ID", style);
        CreateCell(row, 5, "Transport responsibility", style);
        CreateCell(row, 6, "Movement of Hire", style);
        CreateCell(row, 7, "Movement Restitution Code", style);
        CreateCell(row, 8, "Movement Voyage ID", style);
        CreateCell(row, 9, "Equipment owner id", style);
        CreateCell(row, 10, "Movement days", style);
        CreateCell(row, 11, "Movement is last", style);
        CreateCell(row, 12, "Movement shipment UCN", style);
        CreateCell(row, 13, "Movement transport", style);
        CreateCell(row, 14, "Transport means ID", style);
        CreateCell(row, 15, "Transport means comment", style);
        CreateCell(row, 16, "Equipment Prefix", style);
        CreateCell(row, 17, "Equipment Number", style);
        CreateCell(row, 18, "Equipment Check digit", style);
        CreateCell(row, 19, "Equipment type code", style);
        CreateCell(row, 20, "Equipment type length", style);
        CreateCell(row, 21, "Movement type code", style);
        CreateCell(row, 22, "Movement type name", style);
        CreateCell(row, 23, "Equipment status code", style);
        CreateCell(row, 24, "Equipment status Name", style);
        CreateCell(row, 25, "Equipment status level 1", style);
        CreateCell(row, 26, "Equipment status level 2", style);
        CreateCell(row, 27, "Equipment service code", style);
        CreateCell(row, 28, "Equipment service name", style);
        CreateCell(row, 29, "Physical condition code", style);
        CreateCell(row, 30, "Physical condition name", style);
        CreateCell(row, 31, "Physical condition type", style);

        //CreateCell(row, 32, "Movement bound", style);
        //CreateCell(row, 33, "Shipping code", style);
        //CreateCell(row, 34, "Transhipment", style);
        CreateCell(row, 32, "is empty", style);
        //CreateCell(row, 36, "Stowage location", style);
        //CreateCell(row, 37, "Seal number A", style);
        //CreateCell(row, 38, "Seal number B", style);
        CreateCell(row, 33, "Movement comments", style);
        //CreateCell(row, 40, "Transport details", style);
        //CreateCell(row, 41, "Transport reference", style);
        //CreateCell(row, 42, "Haulier", style);
        //CreateCell(row, 43, "Vehicle reference", style);

        CreateCell(row, 34, "From code", style);
        CreateCell(row, 35, "From name", style);
        CreateCell(row, 36, "To code", style);
        CreateCell(row, 37, "To name", style);
        CreateCell(row, 38, "Final code", style);
        CreateCell(row, 39, "Final name", style);
        CreateCell(row, 40, "Restitution code", style);
        CreateCell(row, 41, "Restitution name", style);

        //CreateCell(row, 52, "Depot code", style);
        //CreateCell(row, 53, "Area code", style);
        //CreateCell(row, 54, "location code", style);
        //CreateCell(row, 55, "Street", style);
        //CreateCell(row, 56, "Row", style);
        //CreateCell(row, 57, "Stack", style);

       /* CreateCell(row, 58, "Company line", style);
        CreateCell(row, 59, "Equipment owner code", style);
        CreateCell(row, 60, "Off hire", style);
        CreateCell(row, 61, "Agent code", style);
        CreateCell(row, 62, "Shipper code", style);
        CreateCell(row, 63, "Consignee code", style);*/

        /*CreateCell(row, 64, "Transporting company", style);
        CreateCell(row, 65, "Vessel", style);
        CreateCell(row, 66, "Voyage", style);
        CreateCell(row, 67, "Transport bounds", style);
        CreateCell(row, 68, "Booking process", style);
        CreateCell(row, 69, "Booking line number", style);
        CreateCell(row, 70, "Booking reference", style);
        CreateCell(row, 71, "Goods detail", style);
        CreateCell(row, 72, "UCN", style);
        CreateCell(row, 73, "Number packs", style);
        CreateCell(row, 74, "Net weight", style);
        CreateCell(row, 75, "Gross weight", style);
        CreateCell(row, 76, "Temperature", style);
        CreateCell(row, 77, "Temperature unit", style);
        CreateCell(row, 78, "Commodity code", style);
        CreateCell(row, 79, "Commodity name", style);
        CreateCell(row, 80, "Commodity expire date", style);
        CreateCell(row, 81, "Equipment expire date", style);*/

        /*CreateCell(row, 82, "Goods export declaration", style);
        CreateCell(row, 83, "Customs dispatch document", style);
        CreateCell(row, 84, "Goods value", style);
        CreateCell(row, 85, "Situation", style);
        CreateCell(row, 86, "Customs seal", style);*/

        /*CreateCell(row, 87, "Username", style);
        CreateCell(row, 88, "Audition name", style);
        CreateCell(row, 89, "Audition Time", style);*/

    }
    public void CreateCell(Row row, int columnCount, Object value, CellStyle style){
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if(value instanceof Integer){
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double){
            cell.setCellValue((Double) value);
        } else if (value instanceof Boolean){
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
    private void WriteDataLines(){
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Movement movement : movementList){
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            CreateCell(row, columnCount++, movement.getId(), style);
            CreateCell(row, columnCount++, movement.getDate(), style);
            CreateCell(row, columnCount++, movement.getMovementStatus(), style);
            CreateCell(row, columnCount++, movement.getAccessUserID(), style);
            CreateCell(row, columnCount++, movement.getBusinessUnitID(), style);
            CreateCell(row, columnCount++, movement.getTransportResponsibility(), style);
            CreateCell(row, columnCount++, movement.getMovementOfHire(), style);
            CreateCell(row, columnCount++, movement.getMovementRestitutionCode(), style);
            CreateCell(row, columnCount++, movement.getMovementVoyageID(), style);
            CreateCell(row, columnCount++, movement.getEquipmentOwnerID(), style);
            CreateCell(row, columnCount++, movement.getMovementDays(), style);
            CreateCell(row, columnCount++, movement.isMovementLast(), style);
            CreateCell(row, columnCount++, movement.getShipmentUCN(), style);
            CreateCell(row, columnCount++, movement.getMovementTransport(), style);
            CreateCell(row, columnCount++, movement.getTransportMeans().getId(), style);
            CreateCell(row, columnCount++, movement.getTransportMeans().getComment(), style);
            CreateCell(row, columnCount++, movement.getEquipment().getPrefix(), style);
            CreateCell(row, columnCount++, movement.getEquipment().getNumber(), style);
            CreateCell(row, columnCount++, movement.getEquipment().getCheckDigit(), style);
            CreateCell(row, columnCount++, movement.getEquipment().getEquipmentType().getEquipmentTypeCode(), style);
            CreateCell(row, columnCount++, movement.getEquipment().getEquipmentType().getEquipmentTypeLength(), style);
            CreateCell(row, columnCount++, movement.getMovementType().getMovementTypeCode(), style);
            CreateCell(row, columnCount++, movement.getMovementType().getMovementTypeName(), style);
            CreateCell(row, columnCount++, movement.getEquipmentStatus().getEquipmentStatusCode(), style);
            CreateCell(row, columnCount++, movement.getEquipmentStatus().getEquipmentStatusName(), style);
            CreateCell(row, columnCount++, movement.getEquipmentStatus().getEquipmentStatusLevel1(), style);
            CreateCell(row, columnCount++, movement.getEquipmentStatus().getEquipmentStatusLevel2(), style);
            CreateCell(row, columnCount++, movement.getEquipmentService().getEquipmentServiceCode(), style);
            CreateCell(row, columnCount++, movement.getEquipmentService().getEquipmentServiceName(), style);
            CreateCell(row, columnCount++, movement.getEquipmentCondition().getPhysicalConditionCode(), style);
            CreateCell(row, columnCount++, movement.getEquipmentCondition().getPhysicalConditionName(), style);
            CreateCell(row, columnCount++, movement.getEquipmentCondition().getPhysicalConditionType(), style);
            CreateCell(row, columnCount++, movement.getMovementType().isEmpty(), style);
            CreateCell(row, columnCount++, movement.getMovementComment(), style);
            CreateCell(row, columnCount++, movement.getMovementFromID(), style);//outras tabelas
            CreateCell(row, columnCount++, movement.getMovementToID(), style);
            CreateCell(row, columnCount++, movement.getMovementFinalID(), style);
            CreateCell(row, columnCount++, movement.getMovementRestitutionCode(), style);
        }
    }
    public void ExportExcel(HttpServletResponse response) throws IOException{
        WriteHeaderLine();
        WriteDataLines();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
