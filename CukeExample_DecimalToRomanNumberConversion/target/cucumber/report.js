$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri('conversion/convertSingleDigit.feature');
formatter.feature({
  "id": "convert-single-digit",
  "description": "\nAs film-maker, \nI want to be able to convert a number from decimal to Roman representation,\nSo I can make the film completion date confusing",
  "name": "Convert Single Digit",
  "keyword": "Feature",
  "line": 1
});
formatter.scenario({
  "id": "convert-single-digit;convert-single-digit-decimal-integer",
  "description": "",
  "name": "Convert Single-Digit Decimal Integer",
  "keyword": "Scenario",
  "line": 8,
  "type": "scenario"
});
formatter.step({
  "name": "Input integer is 1",
  "keyword": "Given ",
  "line": 10
});
formatter.step({
  "name": "We convert to Roman representation",
  "keyword": "When ",
  "line": 11
});
formatter.step({
  "name": "We get back an I",
  "keyword": "Then ",
  "line": 12
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 17
    }
  ],
  "location": "ConversionStepDefs.Input_Integer_Is(int)"
});
formatter.result({
  "duration": 118291000,
  "status": "passed"
});
formatter.match({
  "location": "ConversionStepDefs.We_convert_to_roman_representation()"
});
formatter.result({
  "duration": 183000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "I",
      "offset": 15
    }
  ],
  "location": "ConversionStepDefs.We_get_back_an(String)"
});
formatter.result({
  "duration": 2590000,
  "status": "passed"
});
formatter.uri('conversion/dataDrivenDigitConversion.feature');
formatter.feature({
  "id": "convert-several-digits",
  "description": "\nAs film-maker, \nI want to be able to convert numbers from decimal to Roman representation,\nSo I can make film completion dates really confusing",
  "name": "Convert Several Digits",
  "keyword": "Feature",
  "line": 1
});
formatter.scenario({
  "id": "convert-several-digits;convert-single-digit-decimal-integer",
  "description": "",
  "name": "Convert Single-Digit Decimal Integer",
  "keyword": "Scenario",
  "line": 7,
  "type": "scenario"
});
formatter.step({
  "name": "We are converting decimal integers to roman numerals",
  "keyword": "Given ",
  "line": 9
});
formatter.step({
  "name": "We expect the following conversions:",
  "keyword": "When ",
  "line": 11,
  "rows": [
    {
      "cells": [
        "digit",
        "numeral"
      ],
      "line": 13
    },
    {
      "cells": [
        "1",
        "I"
      ],
      "line": 14
    },
    {
      "cells": [
        "2",
        "II"
      ],
      "line": 15
    },
    {
      "cells": [
        "8",
        "VIII"
      ],
      "line": 16
    },
    {
      "cells": [
        "18",
        "XVIII"
      ],
      "line": 17
    },
    {
      "cells": [
        "80",
        "LXXX"
      ],
      "line": 18
    },
    {
      "cells": [
        "81",
        "LXXXI"
      ],
      "line": 19
    }
  ]
});
formatter.step({
  "name": "The conversions turn out as expected",
  "keyword": "Then ",
  "line": 21
});
formatter.match({
  "location": "DataDrivenConversionStepDefs.We_Are_converting_decimal_integers_to_roman_numerals()"
});
formatter.result({
  "duration": 3215000,
  "status": "passed"
});
formatter.match({
  "location": "DataDrivenConversionStepDefs.We_expect_the_following_conversions(DataDrivenConversionStepDefs$Conversion\u003e)"
});
formatter.result({
  "duration": 21102000,
  "status": "passed"
});
formatter.match({
  "location": "DataDrivenConversionStepDefs.The_conversions_turn_out_as_expected()"
});
formatter.result({
  "duration": 30000,
  "status": "passed"
});
});