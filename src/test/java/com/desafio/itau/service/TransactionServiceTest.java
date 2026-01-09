package com.desafio.itau.service;

import com.desafio.itau.entity.Transaction;
import com.desafio.itau.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private TransactionService service;

    private Transaction sample;

    @BeforeEach
    void setUp() {
        sample = new Transaction();
    }

    @Test
    void save_delegatesToRepository() {
        service.save(sample);

        ArgumentCaptor<Transaction> captor = ArgumentCaptor.forClass(Transaction.class);
        verify(repository, times(1)).save(captor.capture());
        assertThat(captor.getValue()).isSameAs(sample);
    }

    @Test
    void findAll_returnsRepositoryData() {
        List<Transaction> expected = List.of(new Transaction(), new Transaction());
        when(repository.findAll()).thenReturn(expected);

        List<Transaction> result = service.findAll();

        assertThat(result).isSameAs(expected);
        verify(repository, times(1)).findAll();
    }

    @Test
    void deleteAll_delegatesToRepository() {
        service.deleteAll();
        verify(repository, times(1)).deleteAll();
    }
}
