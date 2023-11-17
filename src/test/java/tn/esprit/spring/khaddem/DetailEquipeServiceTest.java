//package tn.esprit.spring.khaddem;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import tn.esprit.spring.khaddem.entities.DetailEquipe;
//import tn.esprit.spring.khaddem.repositories.DetailEquipeRepository;
//import tn.esprit.spring.khaddem.services.DetailEquipeServiceImpl;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@ExtendWith(MockitoExtension.class)
//class DetailEquipeServiceTest {
//    @Mock
//    private DetailEquipeRepository detailEquipeRepository;
//
//    @Autowired
//    private DetailEquipeServiceImpl detailEquipeService;
//
//    @BeforeEach
//    public void setUp() {
//        detailEquipeService = new DetailEquipeServiceImpl(detailEquipeRepository);
//    }
//
//    @Test
//    void retrieveAllDetailEquipeTest() {
//        List<DetailEquipe> expectedDetailEquipes = new ArrayList<>();
//        Mockito.when(detailEquipeRepository.findAll()).thenReturn(expectedDetailEquipes);
//        List<DetailEquipe> result = detailEquipeService.retrieveAllDetailEquipe();
//        assertEquals(expectedDetailEquipes.size(), result.size());
//    }
//
//    @Test
//    void addDetailEquipeTest() {
//        DetailEquipe detailEquipe = new DetailEquipe();
//        Mockito.when(detailEquipeRepository.save(detailEquipe)).thenReturn(detailEquipe);
//        DetailEquipe result = detailEquipeService.addDetailEquipe(detailEquipe);
//        assertEquals(detailEquipe, result);
//    }
//
//    @Test
//    void retrieveDetailEquipeTest() {
//        Integer idDetailEquipe = 1; // Change to the desired ID
//        DetailEquipe expectedDetailEquipe = new DetailEquipe();
//        Mockito.when(detailEquipeRepository.findById(idDetailEquipe)).thenReturn(Optional.of(expectedDetailEquipe));
//        DetailEquipe result = detailEquipeService.retrieveDetailEquipe(idDetailEquipe);
//        assertEquals(expectedDetailEquipe, result);
//    }
//}
