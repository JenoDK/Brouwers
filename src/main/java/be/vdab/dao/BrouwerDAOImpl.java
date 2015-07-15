package be.vdab.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import be.vdab.entities.Brouwer;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Beginnaam;

@Repository
public class BrouwerDAOImpl implements BrouwerDAO {

	private final Map<Long, Brouwer> brouwers = new ConcurrentHashMap<>();
	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final BrouwerRowMapper rowMapper = new BrouwerRowMapper();
	private final SimpleJdbcInsert simpleJdbcInsert;
	private static final String BEGIN_SQL = "select id, naam, straat, huisnr, postcode, gemeente, "
			+ "omzet from brouwers ";
	private static final String SQL_FIND_ALL = BEGIN_SQL + "order by naam";
	private static final String SQL_FIND_BY_BEGINNAAM = BEGIN_SQL
			+ "where naam like :beginnaam order by naam";

	@Autowired
	BrouwerDAOImpl(JdbcTemplate jdbcTemplate,
			NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
		simpleJdbcInsert.withTableName("brouwers");
		simpleJdbcInsert.usingGeneratedKeyColumns("id");
	}

	@Override
	public void create(Brouwer brouwer) {
		Map<String, Object> kolomWaarden = new HashMap<>();
		kolomWaarden.put("naam", brouwer.getNaam());
		kolomWaarden.put("straat", brouwer.getAdres().getStraat());
		kolomWaarden.put("huisNr", brouwer.getAdres().getHuisNr());
		kolomWaarden.put("postcode", brouwer.getAdres().getPostcode());
		kolomWaarden.put("gemeente", brouwer.getAdres().getGemeente());
		kolomWaarden.put("omzet", brouwer.getOmzet());
		Number id = simpleJdbcInsert.executeAndReturnKey(kolomWaarden);
		brouwer.setId(id.longValue());
	}

	@Override
	public List<Brouwer> findAll() {
		return jdbcTemplate.query(SQL_FIND_ALL, rowMapper);
	}

	@Override
	public List<Brouwer> findByNaam(String beginNaam) {
		List<Brouwer> brouwersByNaam = new ArrayList<Brouwer>();
		for (Map.Entry<Long, Brouwer> entry : brouwers.entrySet()) {
			if (entry.getValue().getNaam().substring(0, 1).equals(beginNaam)) {
				brouwersByNaam.add(entry.getValue());
			}
		}
		return brouwersByNaam;
	}

	@Override
	public List<Brouwer> findByBeginnaam(Beginnaam beginnaam) {
		Map<String, String> parameters = Collections.singletonMap("beginnaam", beginnaam.getBeginnaam() + "%");
		return namedParameterJdbcTemplate.query(SQL_FIND_BY_BEGINNAAM,
				parameters, rowMapper);
	}

	private static class BrouwerRowMapper implements RowMapper<Brouwer> {
		@Override
		public Brouwer mapRow(ResultSet resultSet, int rowNum)
				throws SQLException {
			return new Brouwer(resultSet.getLong("id"),
					resultSet.getString("naam"), resultSet.getInt("omzet"),
					new Adres(resultSet.getString("straat"),
							resultSet.getString("huisNr"),
							resultSet.getInt("postcode"),
							resultSet.getString("gemeente")));
		}
	}

}
