package kr.co.jhta.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuGuDanImple implements GuGuDanInter {
    int n;

    @Override
    public void print() {
        for (int i = 1; i < 10; i++) {
            System.out.println(n + " * " + i + " = " + i * n);
        }
    }
}
