package me.nick.springbootlearn;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "ipf")
@Component
public class IpsProperties {

    private List<String> blacks;
    private List<String> whites;

    public void setBlacks(List<String> blacks) {
        this.blacks = blacks;
    }
    public void setWhites(List<String> whites) {
        this.whites = whites;
    }
    public List<String> getBlacks() {
        return blacks;
    }
    public List<String> getWhites() {
        return whites;
    }
}
