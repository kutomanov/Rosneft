<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>AJAX JsonArray Example</title>
    <link href='http://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

    <style type="text/css">

        body
        {
            text-align: center;
        }
        .container
        {
            margin-left: auto;
            margin-right: auto;
            width: 40em;
        }

    </style>

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

    <script type="text/javascript">
        $(function() {
            $("#showTable").click(function(event){
                $.get('PopulateTable',function(responseJson) {
                    if(responseJson!=null){
                        $.each(responseJson, function(key,value) {
                            var rev_volume = value['rev_volume'];
                            $("#rev_volume").text(rev_volume.toFixed(2));

                            var rev_volume_2 = rev_volume;
                            $("#rev_volume_2").text(rev_volume_2.toFixed(2));

                            var rev_prise = value['rev_prise'];
                            $("#rev_prise").text(rev_prise.toFixed(2));

                            var purch_prise = value['purch_prise'];
                            $("#purch_prise").text(purch_prise.toFixed(2));

                            var purch_el_resale = rev_volume*purch_prise/1000;
                            $("#purch_el_resale").text(purch_el_resale.toFixed(2));

                            var rev_el_resale = rev_volume*rev_prise/1000;
                            $("#rev_el_resale").text(rev_el_resale.toFixed(2));

                            var volume = rev_volume+rev_volume_2;
                            $("#volume").text(volume.toFixed(2));

                            var price_avarage = (rev_prise+purch_prise)/2;
                            $("#price_avarage").text(price_avarage.toFixed(2));

                            var rev_el_full = rev_el_resale+purch_el_resale;
                            $("#rev_el_full").text(rev_el_full.toFixed(2));

                            var rev_el_full = rev_el_resale+purch_el_resale;
                            $("#rev_el_full").text(rev_el_full.toFixed(2));

                            var marg_income = price_avarage*volume;
                            $("#marg_income").text(marg_income.toFixed(3));
                        });
                    }
                });
            });
        });
    </script>

</head>
<body class="container">
<h1> РОСНЕФТЬ </h1>
<table border="2">
    <tbody>
    <tr>
        <th width="370">Электроэнергия</th>
        <th width="150">Единицы</th>
        <th width="200">Факт</th>
    </tr>
    <tr>
        <td>Маржинальный доход</td>
        <td>тыс.руб</td>
        <td id="marg_income" align="right"></td>
    </tr>
    <tr>
        <td>Выручка за электроэнергию</td>
        <td>тыс.руб</td>
        <td id="rev_el_full" align="right"></td>
    </tr>
    <tr>
        <td>Цена</td>
        <td>руб/МВтч</td>
        <td id="price_avarage" align="right"></td>
    </tr>
    <tr>
        <td>Объем</td>
        <td>МВтч</td>
        <td id="volume" align="right"></td>
    </tr>
    <tr>
        <td>Выручка за электроэнергию перепродажа</td>
        <td>тыс.руб</td>
        <td id="rev_el_resale" align="right"></td>
    </tr>
    <tr>
        <td>Цена</td>
        <td>руб/МВтч</td>
        <td id="rev_prise" align="right"></td>
    </tr>
    <tr>
        <td>Объем</td>
        <td>МВтч</td>
        <td id="rev_volume" align="right"></td>
    </tr>
    <tr>
        <td>Покупка электроэнергии перепродажа</td>
        <td>тыс.руб</td>
        <td id="purch_el_resale" align="right"></td>
    </tr>
    <tr>
        <td>Цена</td>
        <td>руб/МВтч</td>
        <td id="purch_prise" align="right"></td>
    </tr>
    <tr>
        <td>Объем</td>
        <td>МВтч</td>
        <td id="rev_volume_2" align="right"></td>
    </tr>
    </tbody>
</table>
<input style="position:absolute; bottom:328px; left:1020px;" type="button" value="Показать данные" id="showTable"/>
</body>
</html>