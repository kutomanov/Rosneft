import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/PopulateTable")//
public class PopulateTable extends HttpServlet {
private static final long serialVersionUID = 1L;//номер версии для класса

public PopulateTable() {}

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	ArrayList<ResultData> data = new ArrayList<ResultData>();//создаем ArrayList
	data = FetchData.getData();//добавляем в него данные из исходной таблицы
	Gson gson = new Gson();//создаем экземпляр класса
	JsonElement element = gson.toJsonTree(data, new TypeToken<List<ResultData>>() {//преобразуем Java элементы в JsonElement
	}.getType());
	JsonArray jsonArray = element.getAsJsonArray();//добавляем Json элементы в JsonArray
	response.setContentType("application/json");//формирование заголовка
	response.getWriter().print(jsonArray);//выходной поток сервлета
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}