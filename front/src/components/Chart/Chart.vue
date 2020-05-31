

<script>
 import { Bubble } from 'vue-chartjs'
  //Exporting this so it can be used in other components
  export default {
    extends: Bubble,
    props: {
        datacollection: Object
    },
    data () {
        var vm = this
        return {      
            //Chart.js options that controls the appearance of the chart        
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                        beginAtZero: true
                        },
                        gridLines: {
                        display: true
                        }
                    }],
                    xAxes: [ {
                        gridLines: {
                        display: false
                        }
                    }]
                },
                tooltips: {
                    enabled: true,
                    callbacks: {
                        label: ((tooltipItems, data) => {
                            let count = ""
                            if(tooltipItems.datasetIndex === 0 ){                                
                                count = "Количество:" + vm.datacollection.datasets[tooltipItems.datasetIndex].data[tooltipItems.index].r
                            }

                            return vm.datacollection.datasets[tooltipItems.datasetIndex].data[tooltipItems.index].name + ' ' + count
                        })
                    }
                },
            legend: {
                display: true
            },
            responsive: true,
            maintainAspectRatio: false
            }
      }
    },
    mounted () {
      this.renderChart(this.datacollection, this.options)
    }
  }
</script>
<style lang="scss" scoped>
.ant-list-item {
    padding: 12px;
}

</style>