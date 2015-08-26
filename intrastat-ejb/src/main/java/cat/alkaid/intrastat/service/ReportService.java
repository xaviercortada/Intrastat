package cat.alkaid.intrastat.service;

import cat.alkaid.intrastat.model.Category;
import cat.alkaid.intrastat.model.Item;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by xavier on 21/07/15.
 */

@Stateless
@Local
public class ReportService {
    final String NUMBER_FORMAT = "#,##0.00_);[Red](#,##0.00)";

    final String TITLE = "TECNOPLUS";
    final String INTRASTAT_TYPE = "EXPORT";

    final int COLUMN_CODIGO = 0;
    final int COLUMN_FACTURA = 1;
    final int COLUMN_DESC = 2;
    final int COLUMN_PAIS = 3;
    final int COLUMN_PROVEEDOR = 4;
    final int COLUMN_PESO = 5;
    final int COLUMN_TOT_PESO = 6;
    final int COLUMN_IMP = 7;
    final int COLUMN_TOT_IMP = 8;
    final int COLUMN_UNIDADES = 9;
    final int COLUMN_TOT_UNIDADES = 10;

    final String columns[] = {"CODIGO","FACTURA","DESCRIPCIÓN","PAIS","PROVEEDOR","PESO","T.PESO","IMPORTE",
            "T.IMP","UNID","T.UNID","VALOR EST."};

    final int widths[] = {2500,2500,5500,1500,4500,2500,
            2500,2500,2500,2500,2500,3000};


    DataFormat format;

    @EJB
    ItemService itemService;

    @PersistenceContext
    private EntityManager em;

    public Category findById(Long id){
        return em.find(Category.class, id);
    }

    public List<Item> findAll(){
        TypedQuery<Item>query = em.createQuery("SELECT p FROM Item p ORDER BY p.category.name", Item.class);
        return query.getResultList();
    }

    public StreamingOutput Basic(Long idPeriodo){
        final HSSFWorkbook wb = new HSSFWorkbook();

        format = wb.createDataFormat();

        HSSFSheet sheet = wb.createSheet("Tecnoplus");
        sheet.setDefaultRowHeight((short) 265);

        fillTitle(sheet);

        fillHeaders(sheet);

        List<Item> items = itemService.findByPeriodo(idPeriodo);
                //findAll();

        int i = 7;
        int r0 = 0;
        String reportKey = null;
        String tmpKey = null;
        Row row = null;
        for(Item item : items){
            tmpKey = String.format("%s%s%02d",item.getCategory().getCodigo(),
                    item.getEntrega(),
                    item.getPais().getCodigo());
            if(reportKey == null || (!reportKey.equals(tmpKey))){
                if(r0 > 0){
                    writeSubtotal(sheet, r0, i-1);
                    i++;
                }
                writeCategory(sheet, i++, item.getCategory());
                reportKey = tmpKey;
                r0 = i;
            }

            writeItem(sheet, i++, item);

        }

        writeSubtotal(sheet, r0, i-1);

        StreamingOutput streamout = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                wb.write(outputStream);
            }
        };

        return streamout;
    }

    private void writeSubtotal(HSSFSheet sheet, int initRow, int finalRow) {
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);
        cellStyle.setDataFormat(format.getFormat(NUMBER_FORMAT));

        Row row = sheet.createRow(finalRow+1);

        char c = (char)(Character.valueOf('A')+COLUMN_PESO);
        Cell cell = row.createCell(COLUMN_TOT_PESO);
        cell.setCellStyle(cellStyle);
        String formula = String.format("SUM(%c%02d:%c%02d)", c, initRow+1, c, finalRow+1);
        cell.setCellFormula(formula);

        c = (char)(Character.valueOf('A')+COLUMN_IMP);
        cell = row.createCell(COLUMN_TOT_IMP);
        cell.setCellStyle(cellStyle);
        formula = String.format("SUM(%c%02d:%c%02d)", c, initRow+1, c, finalRow+1);
        cell.setCellFormula(formula);

        c = (char)(Character.valueOf('A')+COLUMN_UNIDADES);
        cell = row.createCell(COLUMN_TOT_UNIDADES);
        cell.setCellStyle(cellStyle);
        formula = String.format("SUM(%c%02d:%c%02d)", c, initRow+1, c, finalRow+1);
        cell.setCellFormula(formula);
    }


    private void writeItem(HSSFSheet sheet, int i, Item item){
        Row row = sheet.createRow(i);

        Cell cell = row.createCell(COLUMN_FACTURA);
        fillCellWithValue(cell, item.getFactura());

        cell = row.createCell(COLUMN_DESC);
        fillCellWithValue(cell,item.getEntrega());

        cell = row.createCell(COLUMN_PROVEEDOR);
        fillCellWithValue(cell, item.getProveedor().getName());

        cell = row.createCell(COLUMN_PAIS);
        fillCellWithValue(cell, item.getPais().getSigla());

        cell = row.createCell(COLUMN_PESO);
        fillCellWithValue(cell, item.getPeso());

        cell = row.createCell(COLUMN_IMP);
        fillCellWithValue(cell, item.getPrice());

        cell = row.createCell(COLUMN_UNIDADES);
        fillCellWithValue(cell, item.getUnidades());
    }

    private void writeCategory(HSSFSheet sheet, int i, Category category){
        Row row = sheet.createRow(i);

        Cell cell = row.createCell(COLUMN_CODIGO);
        fillCellWithValue(cell, category.getCodigo());

        cell = row.createCell(COLUMN_DESC);
        fillCellWithValue(cell, category.getName());
    }

    private void fillCellWithValue(Cell cell, Object value){
        if(value != null){
            if(value instanceof String)
                cell.setCellValue((String)value);
            else if(value instanceof Integer)
                cell.setCellValue((Integer)value);
            else if(value instanceof Float) {
                CellStyle style = cell.getCellStyle();
                style.setDataFormat(format.getFormat(NUMBER_FORMAT));
                cell.setCellValue((Float) value);
            }
            else
                cell.setCellValue("");

        }else{
            cell.setCellValue("");

        }
    }

    private void fillTitle(HSSFSheet sheet) {
        Row row = sheet.createRow(2);
        row.setHeight((short)350);

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);

        Cell cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        fillCellWithValue(cell, TITLE);

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        fillCellWithValue(cell, INTRASTAT_TYPE);

    }

    private void fillHeaders(HSSFSheet sheet){
        Row row = sheet.createRow(4);
        row.setHeight((short)350);

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);

        for(int i=0;i<columns.length; i++) {
            sheet.setDefaultColumnStyle(i, cellStyle);
            sheet.setColumnWidth(i, widths[i]);
        }

        CellStyle headerStyle = sheet.getWorkbook().createCellStyle();
        Font headerFont = sheet.getWorkbook().createFont();
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setFontHeightInPoints((short) 10);
        headerStyle.setFont(headerFont);

        for(int i=0;i<columns.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(headerStyle);
            fillCellWithValue(cell, columns[i]);
        }
    }

}
