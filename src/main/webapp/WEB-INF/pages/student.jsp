<!doctype html>
<head>
    <title>Страница студента</title>
    <meta charset="utf-8">
    <style>
        body{
            margin: auto;
            background-color: dodgerblue;
            color: #fff;
            font-family: sans-serif;
            font-weight: 300px;
        }
        
        .center_field{
            height: 100px;
            width: 1024px;
            margin: auto;
        }
        table, th, td{
            border: thin solid white;
        }
        table{
            border-collapse: collapse;
        }
        td{
            width: 200px;
            height: 30px;
            text-align: center;
        }
        tr:nth-child(even){
            background-color: cadetblue;
        }
        th {
            padding-top: 12px;
            padding-bottom: 12px;
            background-color: coral;
            color: white;
        }
        .user_info{
            border: thin solid white;
            padding: 10px;
            margin: 10px;
            height: max-content;
            float: right;
            position: relative;
        }
        .courses_info{
            padding: 10px;
        }
        h3, p{
            margin: 0;
        }
    </style>
</head>
<body>
    <div class="center_field">
        <div class="user_info">
            <p>Jack Nicklson</p>
            <form method="post" action="">
                <input type="submit" name="exit" value="Exit">
            </form>
        </div>
        <div class="courses_info">
            <h4>Available courses</h4>
            <table>
            <tr>
                <th>Course name</th>
                <th>Date</th>
                <th>Info</th>
                <th>Enter</th>
            </tr>
            <tr>
                <td>column1</td>
                <td>column2</td>
                <td>column3</td>
                <td>column3</td>
            </tr>
            <tr>
                <td>column1</td>
                <td>column2</td>
                <td>column3</td>
                <td>column3</td>
            </tr>
            <tr>
                <td>column1</td>
                <td>column2</td>
                <td>column3</td>
                <td>column3</td>
            </tr>
        </table>
        </div>
    </div>
</body>