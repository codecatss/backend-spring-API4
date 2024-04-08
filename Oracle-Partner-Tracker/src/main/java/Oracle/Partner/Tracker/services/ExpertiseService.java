package Oracle.Partner.Tracker.services;

import Oracle.Partner.Tracker.utils.userenum.Status;
import org.springframework.stereotype.Service;
import Oracle.Partner.Tracker.dto.ExpertiseDTO;
import Oracle.Partner.Tracker.entities.Expertise;
import Oracle.Partner.Tracker.repositories.ExpertiseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpertiseService {
    @Autowired
    private ExpertiseRepository expertiseRepository;

    public void saveExpertise(ExpertiseDTO expertiseDTO){
        expertiseRepository.save(new Expertise(expertiseDTO));
    }

    public List<Expertise> findAllExpertise(){
        return expertiseRepository.findAll();
    }

    public Expertise findExpertiseById(String id){
        return expertiseRepository.findById(id).orElse(null);
    }

    public List<ExpertiseDTO> mapCsvToExpertise(MultipartFile file) {
        List<ExpertiseDTO> expertises = new ArrayList<>();
        try {
            String csvDelimiter = ";";
            // Criando um arquivo temporário
            File tempFile = File.createTempFile("temp", ".csv");

            // Salvando o conteúdo do MultipartFile no arquivo temporário
            try (OutputStream os = new FileOutputStream(tempFile)) {
                os.write(file.getBytes());
            }

            // Lendo o arquivo temporário como um arquivo CSV
            try (BufferedReader br = new BufferedReader(new FileReader(tempFile))) {
                String rows;
                String[] header = br.readLine().split(csvDelimiter);
                while ((rows = br.readLine()) != null) {
                    String[] row = rows.split(csvDelimiter);
                    expertises.add(mapRowToExpertise(row, header));
                }
            }
            // Removendo o arquivo temporário depois de usá-lo
            tempFile.delete();
        } catch (IOException e) {
//            e.printStackTrace();
        }
        return expertises;
    }

    public ExpertiseDTO mapRowToExpertise(String[] rows, String[] header) {
        ExpertiseDTO expertiseDTO = new ExpertiseDTO();
        String row;
        for (int i = 0; i < header.length; i++) {
            row = rows[i].replaceAll(" ", "").trim();
            switch (header[i].toLowerCase()) {
                case "service expertise":
                    expertiseDTO.setName(row);
                    break;
                case "description":
                    expertiseDTO.setDescription(row);
                    break;
                case "life time month":
                    expertiseDTO.setLifeTimeMonth(Integer.valueOf(row));
                    break;
                case "status":
                    expertiseDTO.setStatus(Status.toStatus(row));
                    break;
                default:
                    expertiseDTO.setStatus(Status.ACTIVE);
                    break;
            }
        }

        try{
            expertiseRepository.save(new Expertise(expertiseDTO));
        }
        catch (Exception error){}

        return expertiseDTO;
    }
}
