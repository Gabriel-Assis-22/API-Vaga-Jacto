package Desafio_Vaga_Jacto_API.demo.relacional.repository;

import Desafio_Vaga_Jacto_API.demo.relacional.model.Carro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
@Profile({"sqlserver", "dev"})
public class CarroRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Carro> listar() {
        String sql = "SELECT id, marca, ano, disponivel FROM Carro";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Carro carro = new Carro();
            carro.setId(rs.getLong("id"));
            carro.setMarca(rs.getString("marca"));
            carro.setAno(rs.getInt("ano"));
            carro.setDisponivel(rs.getBoolean("disponivel"));
            return carro;
        });
    }

    public Carro buscarPorId(Long id) {
        String sql = "SELECT id, marca, ano, disponivel FROM Carro WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            Carro carro = new Carro();
            carro.setId(rs.getLong("id"));
            carro.setMarca(rs.getString("marca"));
            carro.setAno(rs.getInt("ano"));
            carro.setDisponivel(rs.getBoolean("disponivel"));
            return carro;
        });
    }

    public Carro salvar(Carro carro) {
        final String sql = "INSERT INTO Carro (marca, ano, disponivel) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, carro.getMarca());
                ps.setInt(2, carro.getAno());
                ps.setBoolean(3, carro.isDisponivel());
                return ps;
            }
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            carro.setId(keyHolder.getKey().longValue());
        }

        return carro;
    }

    public Carro atualizar(Long id, Carro carro) {
        String sql = "UPDATE Carro SET marca = ?, ano = ?, disponivel = ? WHERE id = ?";
        jdbcTemplate.update(sql, carro.getMarca(), carro.getAno(), carro.isDisponivel(), id);
        carro.setId(id);
        return carro;
    }

    public void deletar(Long id) {
        String sql = "DELETE FROM Carro WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}