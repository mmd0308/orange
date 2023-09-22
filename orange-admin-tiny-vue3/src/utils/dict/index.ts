import SystemRequest from '@/api/system/index'

function getDict(key: string) {
  let strDicts = sessionStorage.getItem(key)
  if (strDicts) {
    return JSON.parse(strDicts);
  }
  const res = ref([]);
  return (() => {
    SystemRequest.dictData.queryDictDataListByType(key).then((response) => {
      const data = JSON.stringify(response.data)
      sessionStorage.setItem(key, data)
      res.value = response.data
    })
    return toRefs(res.value);
  })()
}

function getDicts(...args: string[]) {
  const res: Ref<Map<string, any>> = ref(new Map)
  return (() => {
    args.forEach((d: string) => {
      let strDicts = sessionStorage.getItem(d)
      if (strDicts) {
        res.value.set(d, JSON.parse(strDicts))
      } else {
        SystemRequest.dictData.queryDictDataListByType(d).then((response) => {
          const data = JSON.stringify(
            response.data
          )
          sessionStorage.setItem(d, data)
          res.value.set(d, data)
        })
      }
    })
    return res.value
  })()
}

export default {
  getDict,
  getDicts
}
