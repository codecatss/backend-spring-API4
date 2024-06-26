package Oracle.Partner.Tracker.controllers;

import Oracle.Partner.Tracker.dto.DashboardDTO;
import Oracle.Partner.Tracker.dto.TrackPerCompany;
import Oracle.Partner.Tracker.entities.Company;
import Oracle.Partner.Tracker.entities.CompanyExpertiseUserCount;
import Oracle.Partner.Tracker.dto.StatePerCompany;
import Oracle.Partner.Tracker.repositories.CompanyRepository;
import Oracle.Partner.Tracker.services.CompanyExpertiseUserCountService;
import Oracle.Partner.Tracker.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.*;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/dash")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private CompanyExpertiseUserCountService companyExpertiseUserCountService;
    @Autowired
    CompanyRepository companyRepository;

    @GetMapping
    public ResponseEntity<DashboardDTO> getAllKPI() {
        DashboardDTO data = dashboardService.getAll();
        if (data == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/track-per-company")
    public ResponseEntity<List<TrackPerCompany>> getTrackPerCompany() {

        List<TrackPerCompany> data = dashboardService.getTrackPerCompany();
        if (data == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/state-per-company")
    public ResponseEntity<List<StatePerCompany>> getStatePerCompany() {
        List<StatePerCompany> data = dashboardService.getStatePerCompany();
        if (data == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(data);
    }

    @GetMapping(path = "/opntrack/visualization")
    public Map<Integer, Map<String, String>> getOpnTrackUsageCount() {
        return dashboardService.getOpnTrackUsageCount();
    }

    @GetMapping(path = "/expertise/visualization")
    public Map<Integer, Map<String, String>> getExpertiseUsageCount() {
        return dashboardService.getExpertiseUsageCount();
    }

    @GetMapping(value = "/companyexpertiseusercountservice")
    public List<CompanyExpertiseUserCount> getCompanyExpertiseUserCountService() {
        return companyExpertiseUserCountService.findAllCompanies();
    }

    @GetMapping(value = "/export/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportToPDF() {
        List<CompanyExpertiseUserCount> trackPerCompany = companyExpertiseUserCountService.findAllCompanies();

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Adicionando Título
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Paragraph title = new Paragraph("Lista de Usuários", font);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" ")); // Linha em branco

            // Criando a Tabela
            PdfPTable table = new PdfPTable(3); // Número de colunas
            table.setWidthPercentage(100);
            table.setWidths(new int[]{1, 3, 3});

            // Cabeçalho da Tabela
            Font headFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Nome da empresa", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Estado", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Expertise", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            // Adicionando dados dos usuários
            for (CompanyExpertiseUserCount ceuc : trackPerCompany) {
                PdfPCell cell;

                cell = new PdfPCell(new Phrase(ceuc.getCompanyName()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(ceuc.getCompanyState().toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(ceuc.getExpertiseName().toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
            }

            document.add(table);
            document.close();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=trackPerCompany.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(out.toByteArray());
    }

      
    @Autowired
    CompanyRepository companyRepository;

    @GetMapping(value = "/certification-per-user")
    public List<Object[]> getUserCertification() {
        return dashboardService.getCertificationsNearExpiration(90);
    }


    @GetMapping(value = "/companyexpertiseusercountservice")
    public List<CompanyExpertiseUserCount> getCompanyExpertiseUserCountService() {
        return companyExpertiseUserCountService.findAllCompanies();
    }



    @GetMapping(value = "/completeworkloads")
    public ResponseEntity<List<Map<String, Object>>> getCompaniesWithCompleteWorkloads() {
        List<Object[]> data = companyRepository.findCompaniesGroupedByWorkloadAndExpertise();
        if (data == null || data.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object[] row : data) {
            String workloadName = (String) row[0];
            String expertiseName = (String) row[1];

            // Procura se já existe um objeto com o nome do workload na lista
            Optional<Map<String, Object>> existingWorkload = resultList.stream()
                    .filter(workload -> workload.get("name").equals(workloadName))
                    .findFirst();

            // Se já existir, adiciona a expertise à lista de expertise desse workload
            if (existingWorkload.isPresent()) {
                Map<String, Object> workload = existingWorkload.get();
                List<String> expertiseList = (List<String>) workload.get("expertises");
                expertiseList.add(expertiseName);
            } else { // Se não existir, cria um novo objeto para o workload
                Map<String, Object> newWorkload = new HashMap<>();
                newWorkload.put("name", workloadName);
                List<String> expertiseList = new ArrayList<>();
                expertiseList.add(expertiseName);
                newWorkload.put("expertises", expertiseList);
                resultList.add(newWorkload);
            }
        }

        return ResponseEntity.ok(resultList);
    }

    @GetMapping(value = "/finance-income")
    public List<Object[]> getAllCompanyAnalysis() {
        return dashboardService.getAllCompanyAnalysis();
    }

    @GetMapping(value = "/finance-expenses")
    public List<Object[]> getExpenses() {
        return dashboardService.getAllCertificationsAnalysisExpired();
    }

    @GetMapping(value = "/finance-profit")
    public List<Object[]> getProfit() {
        return dashboardService.getAllCertificationsAnalysisInProgress();
    }


}