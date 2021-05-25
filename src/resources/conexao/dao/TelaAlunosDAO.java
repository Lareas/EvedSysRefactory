package dao;

//import JDBC.ConnectionFactory;
import entities.TelaAluno;
import funcoes.MyFunc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelaAlunosDAO {

    private Connection con;

    public TelaAlunosDAO() {
       // this.con = new ConnectionFactory().getConnection();

    }

    public List<TelaAluno> pesTelaAluno(String condicao) {

        List<TelaAluno> telaAluno = new ArrayList<>();

        String sql = "SELECT ger.DadoCadastroGeralId, ger.nome, pro.programa, cur.curso, prs.DadoCadastroProgramaSituacao, dcp.anoLetivo, dcp.semestreId, dcp.datacadastro, dcp.localidade, ger.telefonecelular, ger.EmailPessoal "
                + "FROM dadocadastrogeral ger, programa pro, curso cur, dadocadastroprogramasituacao prs, dadocadastroprograma dcp "
                + "WHERE (ger.dadocadastrogeralid = dcp.DadoCadastroGeralId) AND "
                + "(dcp.cursoId = cur.cursoId) AND "
                + "(cur.ProgramaId = pro.ProgramaId) AND "
                + "(dcp.DadoCadastroProgramaSituacaoId = prs.DadoCadastroProgramaSituacaoId) " + condicao;

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(); // executeQuery serve só para trazer dados

            while (rs.next()) {
                TelaAluno p = new TelaAluno();

                p.setCodAluno(rs.getInt("DadoCadastroGeralId"));
                p.setNomeAluno(rs.getString("nome"));
                p.setTitulacao(rs.getString("programa"));
                p.setPrograma(rs.getString("curso"));
                p.setSituacaoNome(rs.getString("DadoCadastroProgramaSituacao"));
                p.setAnoLetivo(rs.getShort("anoLetivo"));
                p.setSemestre(rs.getShort("semestreId"));
                p.setDataCadastro(rs.getDate("datacadastro"));
                p.setLocalidade(rs.getString("Localidade"));
                p.setTelefoneCelular(rs.getString("telefoneCelular"));
                p.setEmailPessoal(rs.getString("emailPessoal"));

                // lança objeto Pessoa (linha) no ArrayList
                telaAluno.add(p);
            }
            stmt.close();
            rs.close();
            con.close();
            MyFunc.mostraMsg("Deve ter dado certo", sql, 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Erro, pesqusa de telaAluno não foi retornada");
            return null;
        }
        return telaAluno;

    }
}
