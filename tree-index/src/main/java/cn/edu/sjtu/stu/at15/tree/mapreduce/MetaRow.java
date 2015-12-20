package cn.edu.sjtu.stu.at15.tree.mapreduce;

/**
 * Created by at15 on 15-12-20.
 */
public class MetaRow {


    private Integer partitionId;
    private Integer start;
    private Integer end;
    private Long count;


    private String indexPath;

    public MetaRow() {
        partitionId = null;
        start = null;
        end = null;
        count = null;
        indexPath = null;
    }

    public MetaRow(String line) {
        String[] columns = line.split("\\t");
        partitionId = Integer.parseInt(columns[0]);
        start = Integer.parseInt(columns[1]);
        end = Integer.parseInt(columns[2]);
        if (columns.length > 3) {
            count = Long.parseLong(columns[3]);
        }
        if (columns.length > 4) {
            indexPath = columns[4];
        }
    }

    public Integer getPartitionId() {
        return partitionId;
    }

    public String getPartitionIdAsString() {
        return String.valueOf(partitionId);
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

    public String getIndexPath() {
        return indexPath;
    }

    public void setIndexPath(String indexPath) {
        this.indexPath = indexPath;
    }

    public String withOutPartitionId() {
        String s = start + "\t" + end;
        if (count != null) {
            s = s + "\t" + count;
        }
        if (indexPath != null) {
            s = s + "\t" + indexPath;
        }
        return s;
    }

    @Override
    public String toString() {
        // TODO: check if partition is null
        return partitionId + "\t" + withOutPartitionId();
    }
}
