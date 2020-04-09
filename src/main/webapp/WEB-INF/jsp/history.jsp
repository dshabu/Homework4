<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Previous Conversions</title>
    <link rel="stylesheet" href="/bulma.min.css">
    <style>
        .header {
            display: grid;
            grid-template-columns: auto;
            grid-template-rows: 100px min-content;
        }
        .header * {
            align-self: center;
            justify-self: center;
        }
    </style>
</head>
<body>
    <div class="header">
        <h1 class="title is-3">Previous Conversions</h1>
        <div class="field has-addons">
            <p class="control">
                <a href="/" class="button is-small">Convert</a>
            </p>
        </div>
    </div>
    <br>
    <div class="columns is-centered">
        <table class="table is-striped">
            <thead>
                <th>Inserted</th>
                <th>From Currency</th>
                <th>From Amount</th>
                <th>To Currency</th>
                <th>To Amount</th>
                <th>Action</th>
            </thead>
            <tbody>
                <c:forEach var="hL" items="${historyList}">
                    <tr>
                        <td>${hL.inserted}</td>
                        <td>${hL.fromCurrency}</td>
                        <td>${hL.fromAmt}</td>
                        <td>${hL.toCurrency}</td>
                        <td>${hL.toAmt}</td>
                        <td><a class="button is-small is-danger" href="/history/delete/${hL.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>