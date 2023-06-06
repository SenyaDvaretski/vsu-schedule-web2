package com.vsuscheduleweb.parser;

public enum ParserStage {
    PARSER_STAGE_LESSONS,

    PARSER_STAGE_SKIP,

    PARSER_STAGE_DATE,
    PARSER_STAGE_GROUPS_ID,
    PARSER_STAGE_SUBGROUPS,
    PARSER_STAGE_NAME_OF_GROUPS,

    PARSER_STAGE_DAY,
    PARSER_STAGE_TIME,

    PARSER_STAGE_TEACHERS,
    PARSER_STAGE_AUDITORIUMS;



}
