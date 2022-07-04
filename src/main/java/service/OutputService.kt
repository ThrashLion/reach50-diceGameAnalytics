package service

import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter

class OutputService {
    companion object {
        fun exportCSV(data: MutableList<List<String>>) {
            csvWriter().writeAll(data, "output.csv")
        }
    }
}