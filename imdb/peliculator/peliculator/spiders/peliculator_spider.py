import scrapy
import re
import csv

from peliculator.items import PeliculatorItem
from scrapy.http import Request

class PeliculatorSpider(scrapy.Spider):
	name = "peliculator"
	allowed_domains = ["imdb.com"]
	start_urls = [
		'http://www.imdb.com/search/title?genres=animation&view=simple',
		'http://www.imdb.com/search/title?genres=biography&view=simple',
		'http://www.imdb.com/search/title?genres=comedy&view=simple',
		'http://www.imdb.com/search/title?genres=crime&view=simple',
		'http://www.imdb.com/search/title?genres=drama&view=simple',
		'http://www.imdb.com/search/title?genres=family&view=simple',
		'http://www.imdb.com/search/title?genres=fantasy&view=simple',
		'http://www.imdb.com/search/title?genres=film_noir&view=simple',
		'http://www.imdb.com/search/title?genres=horror&view=simple',
		'http://www.imdb.com/search/title?genres=music&view=simple',
		'http://www.imdb.com/search/title?genres=mystery&view=simple',
		'http://www.imdb.com/search/title?genres=romance&view=simple',
		'http://www.imdb.com/search/title?genres=sci_fi&view=simple',
		'http://www.imdb.com/search/title?genres=sport&view=simple',
		'http://www.imdb.com/search/title?genres=thriller&view=simple',
		'http://www.imdb.com/search/title?genres=war&view=simple',
		'http://www.imdb.com/search/title?genres=western&view=simple',
	]

	def parse(self, response):
		start = 'genres='
		end = '&view'
		ids = []

		page = re.search('%s(.*)%s' % (start, end), response.url).group(1)

		for href in response.xpath("//div[contains(@class, 'col-title')]/span/span"):
			url =  href.xpath('a/@href').extract_first()
			if url is not None:
				ids.append(url)

		filename = '%s.csv' % page
		with open(filename, 'ab+') as f:
			wr = csv.writer(f,quoting=csv.QUOTE_ALL,lineterminator=",")
			wr.writerow(ids)


		self.log('Saved file %s' %filename)

		next_page = response.xpath("//div[contains(@class, 'nav')]/div[contains(@class, 'desc')]/a[contains(@class, 'lister-page-next')]/@href").extract_first()
		if next_page is not None:
			next_page = response.urljoin(next_page)
			yield scrapy.Request(next_page, callback=self.parse)

