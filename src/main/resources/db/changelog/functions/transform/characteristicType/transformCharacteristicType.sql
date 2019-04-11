create or replace function transform_characteristic_type(wqp_data_source character varying, schema_name character varying)
returns void
language plpgsql
as $$
declare
    code_table_name varchar := 'char_type_swap_' || wqp_data_source;
    source_table_name varchar := 'result_swap_' || wqp_data_source;
begin
    execute format(
        'insert into %I.%I (data_source_id,
                            code_value)
         select distinct data_source_id,
                         characteristic_type code_value
           from %I.%I
          where characteristic_type is not null',
        schema_name, code_table_name, schema_name, source_table_name);
end
$$