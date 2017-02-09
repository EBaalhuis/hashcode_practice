library(magrittr)
formatting_input <- function(file){
    input <- scan(file = file)
    result <- list()
    result$max_x = input[1]
    result$max_y = input[2]
    result$drones = input[3]
    result$deadline = input[4]
    result$maxload = input[5]

    result$num_items <- input[6]
    result$weights <- input[7:(6+result$num_items)]
    cur <- 6 + result$num_items + 1
    result$num_warehouses <- input[cur]

    per_warehouse <- (2 + result$num_items)
    result$warehouse_details <- input[(cur+1):(cur+1 + per_warehouse * result$num_warehouses)] %>%
        matrix(nrow = result$num_warehouses, ncol = per_warehouse)
    cur = (cur+1 + per_warehouse * result$num_warehouses) + 1

    result$num_customers <- input[cur]
    customers <- list()
    index <- cur
    for(i in 1:result$num_customers){
        cust <- list()
    }

}

x = formatting_input("example.in")
