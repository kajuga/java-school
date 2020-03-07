<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of categories</title>
</head>
<body>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
    $(document).ready(function() {
        var table = $('<table/>').appendTo($('#somediv'));
        $.getJSON('/categories', function(persons) {
            persons.each(function(i, person) {
                $('<tr/>').appendTo(table)
                    .append($('<td/>').text(person.name))
                    .append($('<td/>').text(person.address));
            });
        });
    });
</script>

</body>
</html>
