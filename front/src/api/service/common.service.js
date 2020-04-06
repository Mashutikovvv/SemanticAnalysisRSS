export default api => {
	api.common = {
		getNews(getNewsDto) {
            return api.instance.request({
                method: 'post',
                url: '/fetchNews',
                data: getNewsDto
            })
        },
        getAnalysis(getAnalysisDto) {
            return api.instance.request({
                method: 'post',
                url: '/getAnalysis',
                data: getAnalysisDto
            })
        },
	}
}
