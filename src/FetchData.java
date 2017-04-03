import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class FetchData {

	static Connection connection = null;
	static PreparedStatement statement = null;
	static ResultSet rs = null;
	
	public static Connection getConnection() {
		if (connection != null)
		    return connection;
		else {
		    try {
		        Properties prop = new Properties();
				InputStream inputStream = FetchData.class.getClassLoader().getResourceAsStream("db.properties");
				prop.load(inputStream);//грузим данные
				String driver = prop.getProperty("driver");//драйвер к БД
				String url = prop.getProperty("url");//url к БД
				String user = prop.getProperty("user");//имя пользователя БД
				String password = prop.getProperty("password");//пароль к БД
				Class.forName(driver);//загружаем драйвер
				connection = DriverManager.getConnection(url, user, password);
		    } catch (ClassNotFoundException e) {e.printStackTrace();}
				catch (SQLException e) {e.printStackTrace();}
				catch (FileNotFoundException e) {e.printStackTrace();}
				catch (IOException e) {e.printStackTrace();}
				return connection;
		}
	}

		public static ArrayList<ResultData> getData() {//метод считывания всех исходных данных
		ArrayList<ResultData> dataList = new ArrayList<ResultData>();//создаем ArrayList
		ResultData data = null;
		try {
			connection = getConnection();//соединяемся с базой
			statement = connection.prepareStatement("select abs(sum(`ИВ1-`) + sum(`ИВС-`)) as rev_volume, (\n" +
					"select sum(abs(`ИВ1-` + `ИВС-`)*`Цена РСВ`)/(select abs(sum(`ИВ1-`) + sum(`ИВС-`)))) as rev_prise, " +
					"(select sum(abs(`ИВ1-` + `ИВС-`)*least(abs(`ИБР`), abs(`Ц заявки`)))/(select abs(sum(`ИВ1-`) + sum(`ИВС-`)))) as purch_prise from `tbl`");//посылаем запрос
			rs = statement.executeQuery();//получаем ответ от базы
			while(rs.next()) {//пока данные считываются...
				data = new ResultData();//создает объект
				data.setRev_volume(rs.getDouble("rev_volume"));
				data.setRev_prise(rs.getDouble("rev_prise"));
				data.setPurch_prise(rs.getDouble("purch_prise"));
				dataList.add(data);//добавляем данные из таблицы в ArrayList
				for (ResultData d : dataList) {
					System.out.println(d);//выводим данные на консоль
				}
			}
			} catch (SQLException e) {e.printStackTrace();}
		return dataList;//возвращаем ArrayList
		}
}