package tn.esprit.spring.khaddem;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.khaddem.entities.DetailEquipe;
import tn.esprit.spring.khaddem.repositories.DetailEquipeRepository;
import tn.esprit.spring.khaddem.repositories.EquipeRepository;
import tn.esprit.spring.khaddem.services.DetailEquipeServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
class DetailEquipeServiceTest {

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private DetailEquipeRepository detailEquipeRepository;

    @InjectMocks
    private DetailEquipeServiceImpl detailEquipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void retrieveAllDetailEquipe() {
        when(detailEquipeRepository.findAll()).thenReturn(Arrays.asList(new DetailEquipe(), new DetailEquipe()));
        List<DetailEquipe> result = detailEquipeService.retrieveAllDetailEquipe();
        verify(detailEquipeRepository, times(1)).findAll();
        assert !result.isEmpty();
    }

    @Test
    void addDetailEquipe() {
        DetailEquipe detailEquipe = new DetailEquipe();
        when(detailEquipeRepository.save(any(DetailEquipe.class))).thenReturn(detailEquipe);
        DetailEquipe result = detailEquipeService.addDetailEquipe(detailEquipe);
        verify(detailEquipeRepository, times(1)).save(any(DetailEquipe.class));
        assert result != null;
    }

    @Test
    void retrieveDetailEquipe() {
        when(detailEquipeRepository.findById(anyInt())).thenReturn(Optional.of(new DetailEquipe()));
        DetailEquipe result = detailEquipeService.retrieveDetailEquipe(1);
        verify(detailEquipeRepository, times(1)).findById(anyInt());
        assert result != null;
    }
}