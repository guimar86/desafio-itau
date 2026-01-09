package com.desafio.itau.service;

import com.desafio.itau.config.StatsConfiguration;
import com.desafio.itau.entity.Statistics;
import com.desafio.itau.entity.Transaction;
import com.desafio.itau.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StatisticServiceTest {

    @Mock
    private TransactionRepository repository;

    @Mock
    private StatsConfiguration statsConfiguration;

    @InjectMocks
    private StatisticService service;

    @Test
    void statistics_withMixedValues_computesAllMetricsCorrectly() {
        when(statsConfiguration.minutes()).thenReturn(5);

        LocalDateTime now = LocalDateTime.now();
        List<Transaction> data = List.of(
                new Transaction(new BigDecimal("10.00"), now.minusMinutes(1)),
                new Transaction(new BigDecimal("20.00"), now.minusMinutes(2)),
                new Transaction(null, now.minusMinutes(3)),
                new Transaction(new BigDecimal("5.50"), now.minusMinutes(4))
        );
        when(repository.findDataHoraAfter(5L)).thenReturn(data);

        Statistics stats = service.Statistics();

        assertThat(stats.getCount()).isEqualTo(4L);
        assertThat(stats.getSum()).isEqualByComparingTo(new BigDecimal("35.50"));
        assertThat(stats.getAverage()).isEqualByComparingTo(new BigDecimal("11.83"));
        assertThat(stats.getMin()).isEqualByComparingTo(new BigDecimal("5.50"));
        assertThat(stats.getMax()).isEqualByComparingTo(new BigDecimal("20.00"));

        verify(statsConfiguration, times(1)).minutes();
        verify(repository, times(1)).findDataHoraAfter(5L);
    }

    @Test
    void statistics_emptyList_returnsZeros() {
        when(statsConfiguration.minutes()).thenReturn(10);
        when(repository.findDataHoraAfter(10L)).thenReturn(List.of());

        Statistics stats = service.Statistics();

        assertThat(stats.getCount()).isZero();
        assertThat(stats.getSum()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(stats.getAverage()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(stats.getMin()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(stats.getMax()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void statistics_rounding_halfUpToTwoDecimals() {
        when(statsConfiguration.minutes()).thenReturn(3);

        LocalDateTime now = LocalDateTime.now();
        // Values 1, 2, 2 -> average = 5 / 3 = 1.666... -> 1.67 (HALF_UP)
        List<Transaction> data = List.of(
                new Transaction(new BigDecimal("1"), now.minusMinutes(1)),
                new Transaction(new BigDecimal("2"), now.minusMinutes(1)),
                new Transaction(new BigDecimal("2"), now.minusMinutes(2))
        );
        when(repository.findDataHoraAfter(3L)).thenReturn(data);

        Statistics stats = service.Statistics();

        assertThat(stats.getAverage()).isEqualByComparingTo(new BigDecimal("1.67"));
        assertThat(stats.getSum()).isEqualByComparingTo(new BigDecimal("5"));
        assertThat(stats.getMin()).isEqualByComparingTo(new BigDecimal("1"));
        assertThat(stats.getMax()).isEqualByComparingTo(new BigDecimal("2"));
    }
}
