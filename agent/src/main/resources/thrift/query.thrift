namespace java com.shawn.finance.statement.rpc

service Query{
    string query(1:i32 type, 2:string startTime, 3:string endTime, 4:i32 page, 5:i32 perPage)
}