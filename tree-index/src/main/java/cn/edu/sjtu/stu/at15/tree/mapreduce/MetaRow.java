package cn.edu.sjtu.stu.at15.tree.mapreduce;

/**
 * Created by at15 on 15-12-20.
 */
public class MetaRow {


    private Integer partitionId;
    private Integer start;
    private Integer end;
    private Long count;

    public MetaRow() {
        partitionId = null;
        start = null;
        end = null;
        count = null;
    }

    public MetaRow(String line) {
        String[] columns = line.split("\\t");
        partitionId = Integer.parseInt(columns[0]);
        start = Integer.parseInt(columns[1]);
        end = Integer.parseInt(columns[2]);
        if (columns.length > 3) {
            count = Long.parseLong(columns[3]);
        }
    }

    public Integer getPartitionId() {
        return partitionId;
    }

    public void setPartitionId(Integer partitionId) {
        this.partitionId = partitionId;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        // TODO: check if partition is null
        String s = partitionId + "\t" + start + "\t" + end;
        if (count != null) {
            return s + "\t" + count;
        }
        return s;
    }
}
